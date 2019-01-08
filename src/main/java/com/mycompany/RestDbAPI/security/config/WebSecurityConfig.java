package com.mycompany.RestDbAPI.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        String user1 = "admin";
        String user2 = "subadmin"; 
        
        String password = "123";
        
        String encrytedPassword = this.passwordEncoder().encode(password);
        System.out.println("Encoded password of 123=" + encrytedPassword);
         
         
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
        mngConfig = auth.inMemoryAuthentication();
 
        UserDetails u1 = User.withUsername(user1)
                .password(encrytedPassword).roles("USER").build();
        UserDetails u2 = User.withUsername(user2)
                .password(encrytedPassword).roles("USER").build();
 
        mngConfig.withUser(u1);
        mngConfig.withUser(u2);
    }
    
}// end WebConfig
