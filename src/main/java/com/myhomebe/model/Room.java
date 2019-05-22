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
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private @NonNull String name;
  private @NonNull String type;
  private String description;

  @OneToMany(mappedBy = "room")
  // @JsonIgnoreProperties("room")
  private List<RoomMaterial> roomMaterials = new ArrayList<>();
  // Set<RoomMaterial> materials;

  // @ManyToMany(cascade = CascadeType.ALL)
  // @JoinTable(
  //   name = "room_materials",
  //   joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
  //   inverseJoinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id")
  // )
  // private Set<Material> materials  = new HashSet<>();
}
