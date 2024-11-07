package com.notify.service;

import java.io.File;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
    void sendEmail(String[] to,String subject, String message);

    void sendEmailWithHTML(String to, String subject, String htmlContent);
    void sendEmailWithFile(String to, String subject,String message ,File fileName);
}
