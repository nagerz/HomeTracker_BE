package com.myhomebe;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Project {
    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id", description = "A project's id")
    private Long id;
    @GraphQLQuery(name = "name", description = "A project's name")
    private @NonNull String name;
    @GraphQLQuery(name = "description", description = "A project's description")
    private String description;
    @GraphQLQuery(name = "address", description = "A project's address")
    private String address;
}
