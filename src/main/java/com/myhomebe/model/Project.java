package com.myhomebe.model;

import lombok.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
// @ToString
// @EqualsAndHashCode

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLQuery(name = "id", description = "A project's id")
    private Long id;
    @GraphQLQuery(name = "name", description = "A project's name")
    private @NonNull String name;
    @GraphQLQuery(name = "description", description = "A project's description")
    private String description;
    @GraphQLQuery(name = "address", description = "A project's address")
    private String address;
    @GraphQLQuery(name = "city", description = "A project's city")
    private String city;
    @GraphQLQuery(name = "state", description = "A project's state")
    private String state;
    @GraphQLQuery(name = "zip_code", description = "A project's zip code")
    private String zip_code;

    @OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("project")
    private List<Room> rooms = new ArrayList<>();
}
