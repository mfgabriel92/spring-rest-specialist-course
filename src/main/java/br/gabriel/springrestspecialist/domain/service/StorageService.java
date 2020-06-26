package br.gabriel.springrestspecialist.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface StorageService {
    void store(NewFile file);
    
    @Getter
    @Builder
    class NewFile {
        private String filename;
        
        private InputStream inputStream;
    }
}