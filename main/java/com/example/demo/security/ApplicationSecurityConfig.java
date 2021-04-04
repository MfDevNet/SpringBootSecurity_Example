package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.concurrent.TimeUnit;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()

                .and()
//                .httpBasic()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses", true)
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("somthingverysecured")
                .rememberMeParameter("remember-me")
                .and()
        .logout()
        .logoutUrl("/logout")
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID","remember-me")
        .logoutSuccessUrl("/login");



        ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails studentUser = User.builder().username("student") // user
                .password(passwordEncoder.encode("password")) // password
//                .roles(STUDENT.name()) // ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails adminUser = User.builder().username("admin")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name()) // ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails traineeUser = User.builder().username("trainee")
                .password(passwordEncoder.encode("password"))
//                .roles(TRAINEE.name()) //ROLE_TRAINEE
                .authorities(TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                studentUser
                , adminUser
                , traineeUser
        );
    }
}
