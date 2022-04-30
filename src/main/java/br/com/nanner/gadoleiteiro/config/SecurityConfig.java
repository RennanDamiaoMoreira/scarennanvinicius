package br.com.nanner.gadoleiteiro.config;

import br.com.nanner.gadoleiteiro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**")
                .permitAll()
                .antMatchers("/api/v1/cow/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/events/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/cattle_shed/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/productions/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/situations/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/user/**")
                .hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .cors();
        ;
        http.headers().frameOptions().disable();
    }
}
