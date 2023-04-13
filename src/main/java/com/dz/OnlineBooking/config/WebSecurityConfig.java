package com.dz.OnlineBooking.config;

import com.dz.OnlineBooking.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MongoUserService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .authorities("ROLE_ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/register", "/user/register", "/").permitAll()
                .antMatchers("/swagger-ui.html#/**").permitAll()
                //.antMatchers("/appointments/**","/user/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().rememberMe().key("uniqueAndSecret");
    }
}
