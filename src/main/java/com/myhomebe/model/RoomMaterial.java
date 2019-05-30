package com.myhomebe.model;

import lombok.*;

import javax.persistence.*;

//to avoid JSON recursion
import com.fasterxml.jackson.annotation.*;

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
  private Room room;

  @ManyToOne
  @JoinColumn(name = "material_id", nullable = false)
  private Material material;
}
