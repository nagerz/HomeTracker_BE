package com.myhomebe.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private @NonNull String name;

    @Column(name = "type")
    private @NonNull String type;

    @Column(name = "description")
    private String description;
}
