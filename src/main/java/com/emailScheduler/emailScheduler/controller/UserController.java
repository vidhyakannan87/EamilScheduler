package com.emailScheduler.emailScheduler.controller;

import com.emailScheduler.emailScheduler.model.ApplicationUserPersonal;
import com.emailScheduler.emailScheduler.service.ApplicationUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

  private final ApplicationUserService applicationUserService;

  public UserController(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @GetMapping("")
  ResponseEntity getAllUsers() {
    List<ApplicationUserPersonal> applicationUserList = applicationUserService.getAll();
    return ResponseUtility.buildOkResponse(applicationUserList);
  }

}
