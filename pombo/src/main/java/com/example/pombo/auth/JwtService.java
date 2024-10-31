package com.example.pombo.auth;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.pombo.model.entity.Usuario;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String getGenerateToken(Authentication authentication){
        Instant now = Instant.now();

        long dezHorasEmSegundos = 36000L;

        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("pombo").issuedAt(now).expiresAt(now.plusSeconds(dezHorasEmSegundos)).subject(authentication.getName()).claim("roles", roles).claim("idUsuario", usuarioAutenticado.getId()).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    
    }
    
}
