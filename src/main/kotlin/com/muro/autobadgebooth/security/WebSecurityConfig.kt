package com.muro.autobadgebooth.security

import com.muro.autobadgebooth.security.detailsservice.JwtBoothDetailsService
import com.muro.autobadgebooth.security.detailsservice.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class WebSecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


    @Configuration
    @Order(1)
    class UserWebSecurityConfig : WebSecurityConfigurerAdapter() {

        @Autowired
        private lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
        @Autowired
        private lateinit var jwtUserDetailsService: JwtUserDetailsService
        @Autowired
        private lateinit var passwordEncoder: PasswordEncoder

        override fun configure(auth: AuthenticationManagerBuilder?) {
            auth?.userDetailsService(jwtUserDetailsService)?.passwordEncoder(passwordEncoder)
        }

        @Bean(name = ["userAuthentication"])
        override fun authenticationManagerBean(): AuthenticationManager? = super.authenticationManagerBean()

        override fun configure(httpSecurity: HttpSecurity) {
            httpSecurity.csrf().disable()
                    .authorizeRequests().antMatchers(
                            "/user/authenticate",
                            "/create_user"
                    ).permitAll()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
    }

    @Configuration
    @Order(2)
    class BoothWebSecurityConfig : WebSecurityConfigurerAdapter() {

        @Autowired
        private lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
        @Autowired
        private lateinit var jwtBoothDetailsService: JwtBoothDetailsService
        @Autowired
        private lateinit var passwordEncoder: PasswordEncoder

        @Autowired
        override fun configure(auth: AuthenticationManagerBuilder?) {
            auth?.userDetailsService(jwtBoothDetailsService)?.passwordEncoder(passwordEncoder)
        }

        @Bean(name = ["boothAuthentication"])
        override fun authenticationManagerBean(): AuthenticationManager? = super.authenticationManagerBean()

        override fun configure(httpSecurity: HttpSecurity) {
            httpSecurity.csrf().disable()
                    .authorizeRequests().antMatchers(
                            "/booth/authenticate",
                            "/events"
                    ).permitAll()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
    }
}