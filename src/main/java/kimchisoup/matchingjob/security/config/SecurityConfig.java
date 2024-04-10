package kimchisoup.matchingjob.security.config;

import kimchisoup.matchingjob.security.handler.CustomAuthFailureHandler;
import kimchisoup.matchingjob.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String SESSION_COOKIE_KEY = "JSESSIONID";
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthFailureHandler customAuthFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .headers(headerConfig -> headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(matcher -> matcher
                        .anyRequest().permitAll())
                .formLogin(formLoginConfig -> formLoginConfig
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureHandler(customAuthFailureHandler)
                )
                .logout(logoutConfig -> logoutConfig
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies(SESSION_COOKIE_KEY))
                .userDetailsService(customUserDetailsService)
                .rememberMe(rememberConfig -> rememberConfig
                        .tokenValiditySeconds(3600)
                        .alwaysRemember(false))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
