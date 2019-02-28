package com.emailScheduler.emailScheduler.service;


import com.emailScheduler.emailScheduler.model.ApplicationUserPersonal;
import com.emailScheduler.emailScheduler.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserService {

  private ApplicationUserRepository applicationUserRepository;

  public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
    this.applicationUserRepository = applicationUserRepository;
  }

  public List<ApplicationUserPersonal> getAll() {
    return applicationUserRepository.findAll();
  }


  public List<ApplicationUserPersonal> findByBirthDay() {

    List<ApplicationUserPersonal> applicationUserPersonalList = getAll();
    List<ApplicationUserPersonal> birthDayUsers = new ArrayList<>();
    LocalDate today = LocalDate.now();
    int current_month = today.getMonthValue();
    int current_day = today.getDayOfMonth();

    for (ApplicationUserPersonal applicationUserPersonal : applicationUserPersonalList) {

      System.out.println(applicationUserPersonal.getDateOfBirth());
      LocalDate dateOfBirth =LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(applicationUserPersonal.getDateOfBirth()) ) ;
      if (dateOfBirth.getMonthValue() == current_month && dateOfBirth.getDayOfMonth() == current_day) {
        birthDayUsers.add(applicationUserPersonal);

      }

    }
    return birthDayUsers;
  }

}
