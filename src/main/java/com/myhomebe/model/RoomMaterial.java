package com.myhomebe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

//to avoid JSON recursion
import com.fasterxml.jackson.annotation.*;

// Creates getters, setters, equals, hash, and toString methods
// @Data
//Make object ready for storage in a JPA-based data store
@Entity
//Creates(?) table in postgres dB
@Table(name = "room_materials")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class RoomMaterial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String element_type;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  @JsonIgnore
  // @JsonIgnoreProperties("room_materials")
  private Room room;

  @ManyToOne
  @JoinColumn(name = "material_id", nullable = false)
  private Material material;
  // 
  // RoomMaterial() {}
  //
  // RoomMaterial(String element_type) {
  //   this.element_type = element_type;
  // }
}
