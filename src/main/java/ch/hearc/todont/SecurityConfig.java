package ch.hearc.todont;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Based on https://www.baeldung.com/spring-security-login
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/login*").permitAll()
            .antMatchers("/register*").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}