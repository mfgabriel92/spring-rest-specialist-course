package br.gabriel.springrestspecialist.domain.service;

import lombok.Builder;
import lombok.Getter;

public interface MailSenderService {
    void send(EMail mail);
    
    @Getter
    @Builder
    class EMail {
        private String[] recipients;
        
        private String subject;
        
        private String body;
    }
}
