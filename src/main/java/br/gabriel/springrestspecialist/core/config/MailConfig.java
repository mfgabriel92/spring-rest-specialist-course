package br.gabriel.springrestspecialist.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gabriel.springrestspecialist.core.property.MailProperties;
import br.gabriel.springrestspecialist.domain.service.MailSenderService;
import br.gabriel.springrestspecialist.infrastructure.mail.FakeMailSender;
import br.gabriel.springrestspecialist.infrastructure.mail.SmtpMailSender;

@Configuration
public class MailConfig {
    @Autowired
    private MailProperties mailProperties;
    
    @Bean
    public MailSenderService mailService() {
        switch (mailProperties.getProtocol()) {
            case SMTP:
                return new SmtpMailSender();
            default:
                return new FakeMailSender();
        }
    }
}
