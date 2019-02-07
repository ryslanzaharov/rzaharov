package com.example.demo.config;

import com.example.demo.controllers.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger Log = LoggerFactory.getLogger(Registration.class);

    @Autowired
    private DataSource dataSource;

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return super.userDetailsServiceBean();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth
                    .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                            "select username, password, enabled from users "+
                                    " where username=?"
                    )
                    .authoritiesByUsernameQuery(
                            "select u.username, r.roles_name from roles as r, users as u " +
                                    " where r.id = u.roles_id and u.username=?"
                    );

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/addcontacts", "/index", "/editcontacts", "/deletecontacts").hasRole("USER")
                .antMatchers("/login", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
                .and()
                .logout()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
