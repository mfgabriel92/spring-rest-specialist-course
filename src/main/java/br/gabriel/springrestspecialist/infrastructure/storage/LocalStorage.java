package br.gabriel.springrestspecialist.infrastructure.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import br.gabriel.springrestspecialist.core.property.StorageProperties;
import br.gabriel.springrestspecialist.domain.service.StorageService;
import br.gabriel.springrestspecialist.infrastructure.exception.StorageException;

//@Service
public class LocalStorage implements StorageService {
    @Autowired
    private StorageProperties properties;
    
    @Override
    public InputStream find(String filename) {
        try {
            Path path = getPath(filename);
            return Files.newInputStream(path);
        } catch (Exception e) {
            throw new StorageException(String.format("Error while trying to find file '%s'", filename), e);
        }
    }

    @Override
    public void store(NewFile file) {
        try {
            Path path = getPath(file.getFilename());
            FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(path));
        } catch (Exception e) {
            throw new StorageException(String.format("Error while trying to store file '%s'", file.getFilename()), e);
        }
    }
    
    @Override
    public void remove(String filename) {
        try {
            Path path = getPath(filename);
            Files.deleteIfExists(path);
        } catch (Exception e) {
            throw new StorageException(String.format("Error while trying to remove file '%s'", filename), e);
        }
    }
    
    private Path getPath(String filename) {
        return properties.getLocal().getDestination().resolve(Path.of(filename));
//        return resolve(Path.of(filename));
    }
}