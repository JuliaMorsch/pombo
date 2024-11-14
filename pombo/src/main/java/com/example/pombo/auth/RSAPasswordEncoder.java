package com.example.pombo.auth;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RSAPasswordEncoder implements PasswordEncoder {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public RSAPasswordEncoder(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;   
    }
        // Codificação com par de chaves e RSA
        @Override
        public String encode(CharSequence rawPassword) {
            try {
                byte[] encryptedPassword = rawPassword.toString().getBytes(StandardCharsets.UTF_8);

                // Criptografar a senha com a chave pública 
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] encryptedBytes = cipher.doFinal(encryptedPassword);

                return Base64.getEncoder().encodeToString(encryptedBytes);  
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar senha com RSA", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try{
            byte[] encryptedBytes = Base64.getDecoder().decode(encodedPassword);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Comparar senha original com a senha cifrada
            String decryptedPassword = new String(decryptedBytes, StandardCharsets.UTF_8);
            return rawPassword.toString().equals(decryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao decifrar senha com RSA", e);
        }
    }
}
    

