package com.matesgate.project.matesgate.config;


import com.matesgate.project.matesgate.filter.JwtAuthenticationFilter;
import com.matesgate.project.matesgate.service.UserDetailsServiceImpl;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    private final UserDetailsServiceImpl userDetailsServiceImp;
////
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//////
////    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImp) {
////        this.userDetailsServiceImp = userDetailsServiceImp;
////        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        return http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(
////                        req->req.requestMatchers("/api/login/**", "/api/register/**")
////                                .permitAll()
////                                .requestMatchers("/api/worker/auth/**").hasAuthority("WORK")
////                                .anyRequest()
////                                .authenticated()
////                )
////                .userDetailsService(userDetailsServiceImp)
////                .sessionManagement(session->session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
////                .exceptionHandling(
////                        e->e.accessDeniedHandler(
////                                        (request, response, accessDeniedException)->response.setStatus(403)
////                                )
////                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
////                .build();
////    }
////    
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        return configuration.getAuthenticationManager();
////    }
////}
////
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserDetailsServiceImpl userDetailsServiceImp;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImp, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.userDetailsServiceImp = userDetailsServiceImp;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                // Configuration...
//        		.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        req->req.requestMatchers("/api/login/**", "/api/register/**")
//                                .permitAll()
//                                .requestMatchers("/api/worker/auth/**").hasAuthority("WORK")
//                                .anyRequest()
//                                .authenticated()
//                )
//                .userDetailsService(userDetailsServiceImp)
//                .sessionManagement(session->session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                // Configuration...
//                .exceptionHandling(
//                        e->e.accessDeniedHandler(
//                                        (request, response, accessDeniedException)->response.setStatus(403)
//                                )
//                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}



//package com.pixels.Nexum.config;


//import com.pixels.Nexum.filter.JwtAuthenticationFilter;
//import com.pixels.Nexum.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

//    private final CustomLogoutHandler logoutHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImp,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/login/**",  "/api/google/**", "/api/register/**", "/api/worker/show/**")
                                .permitAll()
                                .requestMatchers("/api/worker/auth/**").hasAuthority("WORK")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        e->e.accessDeniedHandler(
                                        (request, response, accessDeniedException)->response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}