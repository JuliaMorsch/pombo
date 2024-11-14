// package com.example.pombo.auth;

// import java.beans.Customizer;
// import java.security.interfaces.RSAPrivateKey;
// import java.security.interfaces.RSAPublicKey;

// import com.nimbusds.jose.jwk.JWK;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import com.nimbusds.jose.jwk.RSAKey;
// import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
// import com.nimbusds.jwt.JWT;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Value("${jwt.public.key}")
//     private RSAPublicKey publicKey;

//     @Value("{jwt.private.key}")
//     private RSAPrivateKey privateKey;

//     @Bean
//     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.
//         cors(cors ->configurationSource(corsConfigurationSource()))
//         .csrf(csrf -> csrf.disable()).authorizeHttpRequests(
//                 auth -> auth.requestMatchers("/authenticate", "/public").permitAll().anyRequest().authenticated())
//                 .httpBasic(Customizer.withDefaults()).oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));

//         return http.build();
//     }

//     @Bean
//     JwtDecoder jwtDecoder() {
//         return NimbusJwtDecoder.withPublicKey(publicKey).build();
//     }

//     @Bean
//     JwtEncoder jwtEncoder() {
//         JWT jwt = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
//         JWTSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWTSet(jwk));
//         return new NimbusJwtEncoder(jwks);
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
