package com.myhomebe.model;

import lombok.*;

import com.fasterxml.jackson.annotation.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GraphQLQuery(name = "id", description = "A room's id")
  private Long id;
  @GraphQLQuery(name = "name", description = "A room's name")
  private @NonNull String name;
  @GraphQLQuery(name = "type", description = "A room's type")
  private @NonNull String type;
  @GraphQLQuery(name = "description", description = "A room's description")
  private String description;

  @OneToMany(mappedBy = "room", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("room")
  private List<RoomMaterial> roomMaterials = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "project_id")
  @JsonIgnoreProperties("rooms")
  Project project;
}
