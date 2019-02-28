package com.emailScheduler.emailScheduler.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email
{
  private String from;
  private String to;
  private String[] cc;
  private String[] bcc;
  private String subject;
  private String text;
  private String mimeType;

}

