package com.example.springboot.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
     private UserDetailsService userDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception { // конфигурация для прохождения аутентификации
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/", "/user").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll()

                .successHandler( new SuccessUserHandler())
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .csrf()
                .disable();
    }


        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}
