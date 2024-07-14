package com.example.kbfinal.config;

import com.example.kbfinal.utill.EncryptionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public EncryptionUtil encryptionUtil() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return new EncryptionUtil();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // /public 경로의 요청을 허용
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )
                .formLogin(formLogin -> formLogin.disable()) // 기본 로그인 페이지 비활성화
//                .httpBasic(httpBasic -> httpBasic.disable()); // HTTP Basic 인증 비활성화
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/users/**", "/h2-console/**")  // CSRF 보호 비활성화 경로 설정
                ).headers(headers -> headers.frameOptions().disable()); // X-Frame-Options 비활성화

        return http.build();
    }


}
