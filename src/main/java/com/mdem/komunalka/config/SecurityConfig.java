package com.mdem.komunalka.config;

import com.mdem.komunalka.security.JWTAuthenticationFilter;
import com.mdem.komunalka.security.JWTLoginFilter;
import com.mdem.komunalka.security.handler.JwtAccessDeniedHandler;
import com.mdem.komunalka.security.handler.JwtAuthenticationFailureHandler;
import com.mdem.komunalka.security.handler.JwtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.mdem.komunalka")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String LOGIN_URL = "/login";

    @Autowired private JWTLoginFilter jwtLoginFilter;
    @Autowired private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()

                // We filter the api/login requests
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)

                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                //.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);
    }

    @Bean
    public JWTLoginFilter getJwtLoginFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) throws Exception {
        JWTLoginFilter filter = new JWTLoginFilter(LOGIN_URL);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Create a default account
        auth.inMemoryAuthentication()
                .withUser("mdem")
                .password("1111")
                .roles("ADMIN");
    }
}
