package com.example.pombo.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pombo.exception.PomboException;

@Service
public class ImagemService {
    
    public String processarImagem(MultipartFile file) throws PomboException {
        byte[] imagemBytes;
        try {
            imagemBytes = file.getBytes();
        } catch (IOException e) {
            throw new PomboException("Erro ao processar o arquivo");
        }

        String base64Imagem = Base64.getEncoder().encodeToString(imagemBytes);

        return base64Imagem;
    }
}
