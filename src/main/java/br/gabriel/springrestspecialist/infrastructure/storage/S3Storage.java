package br.gabriel.springrestspecialist.infrastructure.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.gabriel.springrestspecialist.core.property.StorageProperties;
import br.gabriel.springrestspecialist.domain.service.StorageService;
import br.gabriel.springrestspecialist.infrastructure.exception.StorageException;

@Service
public class S3Storage implements StorageService {
    @Autowired
    private AmazonS3 s3;
    
    @Autowired
    private StorageProperties properties;
    
    @Override
    public FileObject find(String filename) {
        String bucketName = properties.getS3().getBucketName();
        String key = getKey(filename);
        URL url = s3.getUrl(bucketName, key);
        
        return FileObject.builder().url(url.toString()).build();
    }

    @Override
    public void store(NewFile file) {
        try {
            String bucketName = properties.getS3().getBucketName();
            String key = getKey(file.getFilename());
            
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, 
                key, 
                file.getInputStream(), 
                objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);
            
            s3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException(String.format("Error putting object. %s", e.getMessage()), e);
        }
    }

    @Override
    public void remove(String filename) {
        try {
            String bucketName = properties.getS3().getBucketName();
            String key = getKey(filename);
            
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
            
            s3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException(String.format("Error deleting object. %s", e.getMessage()), e);
        }
    }

    private String getKey(String filename) {
        return String.format("%s/%s", properties.getS3().getDestination(), filename);
    }
}