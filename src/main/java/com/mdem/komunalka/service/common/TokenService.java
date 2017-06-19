package com.mdem.komunalka.service.common;

import com.mdem.komunalka.DAO.IAbstractDao;
import com.mdem.komunalka.DAO.impl.UserDao;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.security.CustomAuthenticationToken;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class TokenService {

    private final String SECURITY_KEY;
    private final String TOKEN_DELIMITER;
    private final String MESSAGE_DIGEST_ALGORITHM;
    private final int TOKEN_VALIDITY_TIME;

    private Environment environment;
    private HexBinaryAdapter hexBinaryAdapter;
    private UserDao userDao;


    public TokenService(Environment environment, @Autowired UserDao userDAO, @Autowired HexBinaryAdapter adapter) {
        this.environment = environment;
        this.userDao = userDAO;
        this.hexBinaryAdapter = adapter;
        this.SECURITY_KEY = environment.getProperty("security.tokenEncryptionKey");
        this.TOKEN_DELIMITER = environment.getProperty("security.tokenDelimiter");
        this.MESSAGE_DIGEST_ALGORITHM = environment.getProperty("security.messageDigestAlgorithm");
        this.TOKEN_VALIDITY_TIME = Integer.parseInt(environment.getProperty("security.tokenValidityTimeInSeconds"));
    }

    public void validate(CustomAuthenticationToken token) {
        String[] tokenParts = decodeToken(token.getToken());
        if (tokenParts != null) {
            token.setUserName(tokenParts[0]);

            try {
                token.setExpirationDate(Long.parseLong(tokenParts[1]));
            } catch (NumberFormatException e) {
                System.out.println("Can't parse token expiration data. Date format is incorrect");
                return;
            }

            if (new DateTime(token.getExpirationDate()).isBeforeNow()) {
                System.out.println("Token's validity time expired, new token must be generated");
                return;
            }

            token.setConfirmationKey(tokenParts[2]);
            String expectedKey = buildTokenConfirmationKey(token.getUserName(), token.getExpirationDate());
            if (!expectedKey.equals(token.getConfirmationKey())) {
                System.out.println("Received key does not match the expected one: " + token.getConfirmationKey() + " <> " + expectedKey);
                System.out.println("Details: token is " + token);
                return;
            }

            token.setValid(true);
        }
    }

    public User getUserByToken(CustomAuthenticationToken token) {
        return token.isValid() ? userDao.getUserByLogin(token.getUserName()) : null;
    }

    public String generateTokenFor(String username) {
        long expirationDate = DateTime.now().plusSeconds(TOKEN_VALIDITY_TIME).getMillis();
        String tokenString = buildToken(username, expirationDate);
        return new String(Base64.encodeBase64(tokenString.getBytes()));
    }

    public String renewToken(CustomAuthenticationToken authenticationToken) {
        return authenticationToken.isValid() ? generateTokenFor(authenticationToken.getUserName()) : null;
    }

    private String[] decodeToken(String tokenString) {
        if (isBlank(tokenString) || !Base64.isBase64(tokenString)) {
            return null;
        }

        String token = new String(Base64.decodeBase64(tokenString));
        String[] tokenParts = token.split(TOKEN_DELIMITER);
        if (tokenParts.length != 3) {
            return null;
        }

        for (String tokenPart : tokenParts) {
            if (tokenPart.length() == 0)
                return null;
        }

        return tokenParts;
    }

    private String buildToken(String username, long expirationDate) {
        String tokenConfirmationKey = buildTokenConfirmationKey(username, expirationDate);
        return new StringBuilder()
                .append(username)
                .append(TOKEN_DELIMITER)
                .append(expirationDate)
                .append(TOKEN_DELIMITER)
                .append(tokenConfirmationKey)
                .toString();
    }

    private String buildTokenConfirmationKey(String username, long expirationDate) {
        StringBuilder tokenBuilder = new StringBuilder();
        String token = tokenBuilder
                .append(username)
                .append(expirationDate)
                .append(SECURITY_KEY)
                .toString();

        return hexBinaryAdapter.marshal(DigestUtils.getDigest(MESSAGE_DIGEST_ALGORITHM).digest(getBytesUtf8(token)));
    }
}
