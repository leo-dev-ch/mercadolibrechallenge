package ar.com.leogaray.email.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }
}
