package br.gabriel.springrestspecialist.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import br.gabriel.springrestspecialist.core.property.StorageProperties;
import br.gabriel.springrestspecialist.domain.service.StorageService;
import br.gabriel.springrestspecialist.infrastructure.storage.LocalStorage;
import br.gabriel.springrestspecialist.infrastructure.storage.S3Storage;

@Configuration
public class StorageConfig {
    @Autowired
    private StorageProperties storageProperties;
    
//    @Bean
//    public AmazonS3 amazonS3() {
//        String accessKey = storageProperties.getS3().getAccessKey();
//        String secretKey = storageProperties.getS3().getSecretKey();
//        Regions region = storageProperties.getS3().getRegion();
//        
//        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//        
//        return AmazonS3ClientBuilder.standard()
//            .withCredentials(new AWSStaticCredentialsProvider(credentials))
//            .withRegion(region)
//            .build();
//    }
    
    @Bean
    public StorageService storageService() {
        switch (storageProperties.getStrategy()) {
//            case S3:
//                return new S3Storage();
            default:
                return new LocalStorage();
        }
    }
}
