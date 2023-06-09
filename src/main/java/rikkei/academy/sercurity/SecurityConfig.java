package rikkei.academy.sercurity;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("vuongdaica").password("{noop}123").roles("USER")
                .and().withUser("admin").password("{noop}456").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/auth/user").hasRole("USER")
                .and().authorizeRequests().antMatchers("/auth/admin").hasRole("ADMIN")
                .and().formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
