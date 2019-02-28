package com.emailScheduler.emailScheduler.service;

import com.emailScheduler.emailScheduler.model.ApplicationUserPersonal;
import com.emailScheduler.emailScheduler.model.Email;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.mail.MessagingException;
import java.util.List;


public class QuartzService extends QuartzJobBean {

  private ApplicationUserService applicationUserService;
  private EmailService emailService;

  public QuartzService(ApplicationUserService applicationUserService, EmailService emailService) {
    this.applicationUserService = applicationUserService;
    this.emailService = emailService;
  }

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) {
    List<ApplicationUserPersonal> applicationUserList = applicationUserService.findByBirthDay() ;
    applicationUserList.stream().forEach(applicationUser -> {
      try {
        Email email = new Email();
        email.setFrom("vidhyakannan87@gmail.com");
        email.setSubject("Happy BirthDay");
        email.setTo("vidhya.kannan@allergan.com");
        email.setText("<h1>Dear " + applicationUser.getFirstName() +
                ",Many Many Happy Returns of the day :-)</h1>");

        emailService.sendEmail(email);
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    });


  }
}
