package com.emailScheduler.emailScheduler.controller;


import com.emailScheduler.emailScheduler.service.EmailService;
import com.emailScheduler.emailScheduler.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import java.time.ZonedDateTime;
import java.util.Date;


@RestController
public class EmailController {

  private final EmailService emailService;

  @Autowired
  private Scheduler scheduler;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping(value = "/sendEmail")
  ResponseEntity sendEmail() throws MessagingException {
    try {

      ZonedDateTime now = ZonedDateTime.now();
      JobDetail jobDetail = buildJobDetail();
      Trigger trigger = buildTrigger(jobDetail, now);
      scheduler.scheduleJob(jobDetail, trigger);

    } catch (SchedulerException ex) {
      System.out.println(ex.getLocalizedMessage());
    }

    return ResponseUtility.buildOkResponse();
  }


  private JobDetail buildJobDetail() {
    return JobBuilder.newJob(QuartzService.class)
            .withDescription("Send Email Job")
            .storeDurably()
            .build();

  }

  private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
    return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.getKey().getName(), "email-triggers")
            .withDescription("Send Email Trigger")
            .startAt(Date.from(startAt.toInstant()))
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
            .build();
  }
}

