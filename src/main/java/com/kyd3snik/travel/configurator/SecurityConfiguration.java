package com.kyd3snik.travel.configurator;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Calendar;
import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/signin").defaultSuccessUrl("/user").permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserDetailsManager securityManager() {
        UserDetails[] users = {
                new User(0,
                        "First name",
                        "Last name",
                        "Middle name",
                        Calendar.getInstance().getTime(),
                        true,
                        true,
                        "email@mail.com",
                        "password",
                        new City(0, "Moscow", null, Collections.emptyList())
                ),
                new User(0,
                        "First name another",
                        "Last name another",
                        "Middle name another",
                        Calendar.getInstance().getTime(),
                        true,
                        false,
                        "example@mail.com",
                        "pass",
                        new City(0, "Voronezh", null, Collections.emptyList())
                )
        };
        return new InMemoryUserDetailsManager(users);
    }
}
