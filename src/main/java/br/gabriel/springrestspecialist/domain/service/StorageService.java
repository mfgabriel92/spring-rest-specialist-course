package br.gabriel.springrestspecialist.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface StorageService {
    InputStream find(String filename);
    
    void store(NewFile file);
    
    void remove(String filename);
    
    default String generateFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
    
    @Getter
    @Builder
    class NewFile {
        private String filename;
        
        private String contentType;
        
        private InputStream inputStream;
    }
}