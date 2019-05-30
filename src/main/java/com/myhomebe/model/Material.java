package com.myhomebe.model;

import lombok.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import io.leangen.graphql.annotations.GraphQLQuery;

//Make object ready for storage in a JPA-based data store
@Entity
//Creates(?) table in postgres dB
@Table(name = "materials")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Material {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GraphQLQuery(name = "id", description = "A material's id")
  private Long id;
  @GraphQLQuery(name = "name", description = "A material's name")
  private @NonNull String name;
  @GraphQLQuery(name = "model_number", description = "A material's model number")
  private String model_number;
  @GraphQLQuery(name = "brand", description = "A material's brand")
  private String brand;
  @GraphQLQuery(name = "vendor", description = "A material's vendor")
  private String vendor;
  @GraphQLQuery(name = "manual_url", description = "A material's manual url")
  private String manual_url;
  @GraphQLQuery(name = "notes", description = "A material's notes")
  private String notes;
  @GraphQLQuery(name = "quantity", description = "A material's quantity")
  private Float quantity;
  @GraphQLQuery(name = "unit_price", description = "A material's unit price")
  private Float unit_price;

  @OneToMany(mappedBy = "material", orphanRemoval = true, cascade = CascadeType.PERSIST)
  @JsonIgnoreProperties("material")
  private List<RoomMaterial> roomMaterials = new ArrayList<>();
}
