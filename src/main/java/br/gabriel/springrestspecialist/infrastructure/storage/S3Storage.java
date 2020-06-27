package br.gabriel.springrestspecialist.infrastructure.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import br.gabriel.springrestspecialist.domain.service.StorageService;

@Service
public class S3Storage implements StorageService {
    @Autowired
    private AmazonS3 s3;
    
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