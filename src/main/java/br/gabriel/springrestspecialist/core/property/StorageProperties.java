package br.gabriel.springrestspecialist.core.property;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("srs.storage")
public class StorageProperties {
    private Local local = new Local();
    
    private S3 s3 = new S3();

    @Getter
    @Setter
    public class Local {
        private Path destination;
    }
    
    @Getter
    @Setter
    public class S3 {
        private String accessKey;
        
        private String secretKey;
        
        private String bucketName;
        
        private Regions region;
        
        private String destination;
    }
}
