package com.myhomebe.model;

import lombok.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

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
