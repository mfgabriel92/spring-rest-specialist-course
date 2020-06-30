package br.gabriel.springrestspecialist.infrastructure.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.gabriel.springrestspecialist.domain.service.MailSenderService.EMail;
import br.gabriel.springrestspecialist.infrastructure.exception.MailException;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class MailUtils {
    @Autowired
    private Configuration freemarker;
    
    public String getTemplate(EMail mail) {
        try {
            Template template = freemarker.getTemplate(mail.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getVariables());
        } catch (Exception e) {
            throw new MailException("Error while getting template. " + e.getMessage(), e);
        }
    }
}
