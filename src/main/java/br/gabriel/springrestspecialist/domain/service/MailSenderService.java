package br.gabriel.springrestspecialist.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface MailSenderService {
    void send(EMail mail);
    
    @Getter
    @Builder
    class EMail {
        @Singular
        @NonNull
        private Set<String> recipients;
        
        @NonNull
        private String subject;
        
        @NonNull
        private String body;
        
        @Singular
        private Map<String, Object> variables;
    }
}
