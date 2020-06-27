package br.gabriel.springrestspecialist.infrastructure.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.service.StorageService;

@Service
public class S3Storage implements StorageService {
    @Override
    public InputStream find(String filename) {
        return null;
    }

    @Override
    public void store(NewFile file) {
    }

    @Override
    public void remove(String filename) {
    }
}