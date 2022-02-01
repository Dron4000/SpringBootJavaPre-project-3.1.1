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
    private SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям
    @Autowired
     private UserDetailsService userDetailsService;





    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);// предоставляет юзеров.Чтоб понимал
        authProvider.setPasswordEncoder(passwordEncoder());// ПОДКЛЮЧАЕМ  его
        return authProvider;
    }
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception { // конфигурация для прохождения аутентификации
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// перехватчик URL
                .antMatchers("/", "/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin() // Spring сам подставит свою логин форму
                .successHandler( new SuccessUserHandler()) // подключаем наш SuccessHandler для перенеправления по ролям
                .and()
                .logout().logoutSuccessUrl("/login")// и без него перенаправляет на регистрацию после выхода
                .and()
                .csrf()
                .disable();
    }

    // Необходимо для шифрования паролей
    //
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}
