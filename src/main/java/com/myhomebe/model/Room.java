package com.myhomebe.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.util.Objects;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private @NonNull String name;
  private @NonNull String type;
  private String description;

  Room(String name, String role, String description) {
    this.name = name;
    this.type = type;
    this.description = description;
  }
}
