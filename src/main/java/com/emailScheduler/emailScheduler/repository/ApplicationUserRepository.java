package com.emailScheduler.emailScheduler.repository;

import com.emailScheduler.emailScheduler.model.ApplicationUserPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplicationUserRepository extends JpaRepository<ApplicationUserPersonal, Long> {


}
