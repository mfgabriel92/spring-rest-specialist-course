package br.gabriel.springrestspecialist.infrastructure.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import br.gabriel.springrestspecialist.domain.service.StorageService;
import br.gabriel.springrestspecialist.infrastructure.exception.StorageException;

public class LocalStorage implements StorageService {
    @Value("${srs.storage.local.destination}")
    private Path path;
    
    @Override
    public void store(NewFile file) {
        try {
            Path path = getPath(file.getFilename());
            FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(path));
        } catch (Exception e) {
            throw new StorageException(String.format("Error while trying to store file '%s'", file.getFilename()), e);
        }
    }
    
    private Path getPath(String filename) {
        return path.resolve(Path.of(filename));
    }
}