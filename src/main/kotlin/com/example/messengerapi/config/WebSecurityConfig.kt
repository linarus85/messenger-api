package com.example.messengerapi.config

import com.example.messengerapi.filters.JwtLoginFIlter
import com.example.messengerapi.services.AppUserDetailService
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailService: AppUserDetailService) : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users/register")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/login")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(
                JwtLoginFIlter(
                    "/login",
                    authenticationManager()
                ),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userDetailService)
            .passwordEncoder(BCryptPasswordEncoder())
    }
}