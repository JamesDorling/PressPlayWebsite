package com.sparta.jd.pressplay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource datasource;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SecurityConfig(DataSource datasource, BCryptPasswordEncoder encoder) {
        this.datasource = datasource;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("userpass"))
                .authorities("CUSTOMER");
        //auth.jdbcAuthentication()
        //        .dataSource(datasource)
        //        .usersByUsernameQuery("select email from customer where email=?")
        //        .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/rent/*").authenticated()
                .anyRequest().permitAll()

                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true).permitAll()

                .and().exceptionHandling().accessDeniedPage("/error")
                .and().logout();
    }

}
