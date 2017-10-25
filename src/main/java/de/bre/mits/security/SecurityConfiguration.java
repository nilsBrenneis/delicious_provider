package de.bre.mits.security;


import de.bre.mits.entities.Mitarbeiter;
import de.bre.mits.repositories.MitarbeiterRepository;
import jdk.management.resource.ResourceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Iterator;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM = "MY_PROTO_REALM";

    @Autowired
    MitarbeiterRepository mitarbeiterRepo;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("cook").password("abc123").roles("EVERYONE");
        auth.inMemoryAuthentication().withUser("manager").password("abc123").roles("MANAGER");
        auth.inMemoryAuthentication().withUser("chef").password("abc123").roles("HEAD_CHEF");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/kritBestaende").hasRole("MANAGER") //only manager can see critical stock
                .antMatchers("/bestaende/**").hasAnyRole("MANAGER", "EVERYONE", "HEAD_CHEF") //every staffer can get and edit stock
                .antMatchers("/neuZutat/**").hasRole("HEAD_CHEF") //only chef can put new items in stock
                .anyRequest().authenticated()
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //I do not need sessions
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}