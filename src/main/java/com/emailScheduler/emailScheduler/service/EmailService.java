package com.emailScheduler.emailScheduler.service;


import com.emailScheduler.emailScheduler.model.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

  private JavaMailSender mailSender;

  public  EmailService(JavaMailSender mailSender) {
   this.mailSender = mailSender;
  }

  public void sendEmail(Email email) throws MessagingException {

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    helper.setTo(email.getTo());
    helper.setFrom(email.getFrom());
    helper.setSubject(email.getSubject());
    helper.setText(email.getText(), true);

    mailSender.send(mimeMessage);
  }
}
