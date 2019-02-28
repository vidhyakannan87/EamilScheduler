package com.emailScheduler.emailScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ApplicationUserPersonal {

  @Id
  @JsonIgnore
  private long id;

  @NotBlank
  private String firstName;


  @NotBlank
  private String lastName;

  @NotBlank
  @Column(unique = true)
  private String email;


  @Column(unique = true)
  private String username;

  private Date dateOfBirth;

}
