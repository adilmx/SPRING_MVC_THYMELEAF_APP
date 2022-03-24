package com.adilmx.spring_mvc_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    //TO GET THE SAME DATASOURCE CONFIGURED FOR THE APPLICATION CONTEXT
    private DataSource dataSource;

    public SpringSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //{noop} to ignore the PasswordEncoder by spring security as below example
        //auth.inMemoryAuthentication().withUser("example").password("{noop}example22").roles("USER", "ADMIN");

        //use a password encoder to hash the passwords
        PasswordEncoder passwordEncoder = passwordEncoder();

        //inMemoryAuthentication use an internal memory DB to save users abd roles

        /*auth.inMemoryAuthentication().withUser("root").password(passwordEncoder.encode("root")).roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).roles("USER");
        */

        //jdbc authentication with external users and roles
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username as principal,password as credentials,enabled as active FROM USERS WHERE username=?")
                .authoritiesByUsernameQuery("SELECT USERS.username as principal ,ROLES.authority as role " +
                        "FROM USERS " +
                        "JOIN users_authorities ON USERS.ID=users_authorities.user_id "+
                        "JOIN ROLES ON ROLES.ID=users_authorities.authorities_id "+
                        "WHERE USERS.username=?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authentication input is as a pop-up browser
        //http.httpBasic();
        //authentication inputs are in html form
        http.formLogin().loginPage("/login");

        //authorize all requests to all users
        //http.authorizeRequests().anyRequest().authenticated();

        //filter the authorizations by paths and user's roles
        http.authorizeRequests()
                .antMatchers( "/addContrat/**", "/editContrat/**","/saveContrat/**","/deleteContrat/**").hasRole("ADMIN")
                .antMatchers("/contrats/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/login/**","/webjars/**").permitAll()
                //allow others without checking roles but the have to be authenticated
                .anyRequest().authenticated();

        //enabling the mechanism of CSRF(CROSS SITE REQUEST FORGERY) to prevent the csrf attack
        //which exploit the authenticated user to send a request using a session id for example and force it to do something as(saving/get data ...)
        //so this CSRF add a Synchronized Token to every request (in forms for example we add a hidden input with the value of this Token)
        //this mechanism is by default ENABLED by spring security
        //we can disable it by :
        //http.csrf().disable();
        http.csrf();

        //in case of an error of authorization ,we can customize the error page
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
