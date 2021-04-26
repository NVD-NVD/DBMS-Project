package com.ute.dbms.bkwebshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
                // .antMatchers("/register").permitAll()
                // .antMatchers("/").permitAll()
                // .antMatchers("/user").hasRole("MEMBER")
                // .antMatchers("/admin").hasRole("ADMIN")
                // .and()
                // .formLogin()
                // .loginPage("/login")
                // .usernameParameter("email")
                // .passwordParameter("password")
                // .defaultSuccessUrl("/loginAcce")
                // .failureUrl("/login?error")
                // .and()
                // .exceptionHandling()
                // .accessDeniedPage("/403");
    }

}