package fr.dalichamp.romain.ipfilterspringsecurity.configuration;

import fr.dalichamp.romain.ipfilterspringsecurity.filters.CustomIpFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Spring Security Web Configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll().and()
                .addFilterBefore(new CustomIpFilter(),
                        BasicAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable();
    }
}