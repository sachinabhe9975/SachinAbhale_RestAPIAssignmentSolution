package com.greatlearning.employee_management.security;

import com.greatlearning.employee_management.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    

    @Bean
    public UserDetailsService userDetailsService() {
        return  new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/").permitAll()
              .antMatchers("/user", "/role").hasAuthority("ADMIN")
             .antMatchers(HttpMethod.POST,"/employees").hasAuthority("ADMIN")
          .antMatchers(HttpMethod.PUT,"/employees").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/employees/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
               .and()
               .cors().and().csrf().disable();
        http.headers().frameOptions().disable();   // To view H2 Console
        http .headers().frameOptions().sameOrigin();


    }
    
}