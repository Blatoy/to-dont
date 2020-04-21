package ch.hearc.todont;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
            /*.loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/homepage.html", true)
            //.failureUrl("/login.html?error=true")
            .failureHandler(authenticationFailureHandler())
            .and()
            .logout()
            .logoutUrl("/perform_logout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler())*/;
    }
}