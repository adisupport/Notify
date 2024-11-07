package com.notify.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String sender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setFrom(sender);
        mail.setText(message);

        mailSender.send(mail);
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setFrom(sender);
        mail.setText(message);

        mailSender.send(mail);
    }

    @Override
    public void sendEmailWithHTML(String to, String subject, String htmlContent) {
        MimeMessage mail = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mail,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom(sender);
            mailSender.send(mail);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(sender);
            helper.setText(message,true);
            FileSystemResource fileSystem = new FileSystemResource(file);
            String fileName = fileSystem.getFilename();
            if(fileName != null){
                helper.addAttachment(fileName,file);
            }
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
