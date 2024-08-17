package com.tour.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "destinations")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dest_id")
    private Long destId;

    @Column(name = "dest_name")
    private String destName;

    private String state;

    private String description;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "acco_id", referencedColumnName = "acco_id")
    private Accommodation accommodation;

    // No reference to Tour here for unidirectional relationship
}
