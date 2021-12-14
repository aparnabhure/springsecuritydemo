package com.ap.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //In memory authentication
        auth.inMemoryAuthentication()
                .withUser("aparna").password("aparna").roles("admin")
                //Add multiple users
                .and()
                .withUser("test").password("test").roles("user");

    }

    //Authorization at API level with User Role
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ap/admin").hasRole("admin")
                .antMatchers("/ap/user").hasAnyRole("user","admin")
                .antMatchers("/ap/home").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //This is to override the password encoding, this should not be there in production
        // This is only for this demo app
        return NoOpPasswordEncoder.getInstance();
    }
}


