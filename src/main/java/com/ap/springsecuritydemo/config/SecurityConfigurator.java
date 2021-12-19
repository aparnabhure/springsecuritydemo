package com.ap.springsecuritydemo.config;

import com.ap.springsecuritydemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //JPA authentication with UserDetails
        auth.userDetailsService(userDetailsService);
    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //JDBC authentication with H2 embedded database, which creates database
//        auth.jdbcAuthentication()
//                //This makes connectivity with H2
//                .dataSource(dataSource);
//        //NOTE: Roles are Case sensitive
//
//    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //JDBC authentication with H2 embedded database, which creates database, with adding users
//        auth.jdbcAuthentication()
//                //This makes connectivity with H2
//                .dataSource(dataSource)
//                // This creates default tables like User and Authentication
//                .withDefaultSchema()
//                .withUser(User.withUsername("aparna").password("aparna").roles("admin"))
//                .withUser(User.withUsername("test").password("test").roles("user"));
//
//    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //In memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("aparna").password("aparna").roles("admin")
//                //Add multiple users
//                .and()
//                .withUser("test").password("test").roles("user");
//
//    }



    //Authorization at API level with User Role
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ap/admin").hasRole("ADMIN")
                .antMatchers("/ap/user").hasAnyRole("ADMIN","USER")
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


