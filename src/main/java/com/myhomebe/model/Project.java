package com.myhomebe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;

import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

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

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    private List<Room> rooms = new ArrayList<>();
}
