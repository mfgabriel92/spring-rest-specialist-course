package br.gabriel.springrestspecialist.core.property;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("srs.email")
public class MailProperties {
    @NotNull
    private String from;
    
    private Protocol protocol = Protocol.FAKE;
    
    public enum Protocol {
        FAKE,
        SMTP
    }
}
