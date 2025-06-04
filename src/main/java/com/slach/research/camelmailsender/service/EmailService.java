package com.slach.research.camelmailsender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.from}")
  private String defaultFrom;

  @Value("${spring.mail.default-subject}")
  private String defaultSubject;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendEmail(String to, String subject, String body, List<File> attachments) throws MessagingException {
    MimeMessage message = createMimeMessage(to, subject, body);
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    if (attachments != null) {
      for (File file : attachments) {
        helper.addAttachment(file.getName(), file);
      }
    }

    mailSender.send(message);
  }

  public void sendEmailWithInMemoryAttachment(String to, String subject, String body, byte[] data, String filename)
    throws MessagingException {
    MimeMessage message = createMimeMessage(to, subject, body);
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.addAttachment(filename, new ByteArrayResource(data));

    mailSender.send(message);
  }

  private MimeMessage createMimeMessage(String to, String subject, String body) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(defaultFrom);
    helper.setTo(to);
    helper.setSubject(subject != null ? subject : defaultSubject);
    helper.setText(body, false);
    return message;
  }
}
