package br.gabriel.springrestspecialist.core.property;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("srs.storage")
public class StorageProperties {
    private Local local = new Local();

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
        
        private String region;
        
        private String destination;
    }
}
