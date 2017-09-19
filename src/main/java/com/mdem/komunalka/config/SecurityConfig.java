package com.mdem.komunalka.config;

import com.mdem.komunalka.security.TokenAuthenticationFilter;
import com.mdem.komunalka.security.TokenAuthenticationManager;
import com.mdem.komunalka.security.AuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.mdem.komunalka")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String LOGIN_URL = "/login";
    private String SECURE_URL = "/api/**";

    @Autowired private AbstractAuthenticationProcessingFilter tokenAuthenticationFilter;
    @Autowired private TokenAuthenticationManager tokenAuthenticationManager;

    @Autowired private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public AbstractAuthenticationProcessingFilter getTokenAuthenticationFilter() throws Exception {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(SECURE_URL);
        filter.setAuthenticationManager(tokenAuthenticationManager);
        return filter;
    }
}