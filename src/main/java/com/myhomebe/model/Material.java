package com.myhomebe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// Creates getters, setters, equals, hash, and toString methods
@Data
//Make object ready for storage in a JPA-based data store
@Entity
//Creates(?) table in postgres dB
@Table(name = "materials")

public class Material {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private @NonNull String name;
  private String model_number;
  private String brand;
  private String vendor;
  private String manual_url;
  private String notes;
  private Float quantity;
  private Float unit_price;

  @OneToMany(mappedBy = "material")
  @JsonIgnoreProperties("material")
  private List<RoomMaterial> roomMaterials = new ArrayList<>();
  // Set<RoomMaterial> rooms;

  // @ManyToMany(mappedBy = "materials")
  // private Set<Room> rooms = new HashSet<>();

  Material() {}

  Material(String name, String model_number, String brand, String vendor,
          String manual_url, String notes, Float quantity, Float unit_price) {
    this.name = name;
    this.model_number = model_number;
    this.brand = brand;
    this.vendor = vendor;
    this.manual_url = manual_url;
    this.notes = notes;
    this.quantity = quantity;
    this.unit_price = unit_price;
  }
}
