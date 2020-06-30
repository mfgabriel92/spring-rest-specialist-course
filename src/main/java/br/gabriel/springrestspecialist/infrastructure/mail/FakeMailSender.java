package br.gabriel.springrestspecialist.infrastructure.mail;

import org.springframework.beans.factory.annotation.Autowired;

public class FakeMailSender extends SmtpMailSender {
    @Autowired
    private MailUtils utils;
    
    @Override
    public void send(EMail mail) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(mail.getRecipients());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(utils.getTemplate(mail));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}