package br.gabriel.springrestspecialist.infrastructure.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.gabriel.springrestspecialist.core.property.MailProperties;
import br.gabriel.springrestspecialist.domain.service.MailSenderService;
import br.gabriel.springrestspecialist.infrastructure.exception.MailException;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SmtpMailSender implements MailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private MailProperties mailProperties;
    
    @Autowired
    private Configuration freemarker;
    
    @Override
    public void send(EMail mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(mailProperties.getFrom());
            helper.setTo(mail.getRecipients().toArray(new String[0]));
            helper.setSubject(mail.getSubject());
            helper.setText(getTemplate(mail), true);
            
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailException("Error when trying to send e-mail. " + e.getMessage(), e);
        }
    }
    
    private String getTemplate(EMail mail) {
        try {
            Template template = freemarker.getTemplate(mail.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getVariables());
        } catch (Exception e) {
            throw new MailException("Error while getting template. " + e.getMessage(), e);
        }
    }
}