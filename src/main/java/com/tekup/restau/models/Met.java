package com.tekup.restau.models;


import com.tekup.restau.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "met_type_id",
        columnDefinition = "TEXT"
)
public class Met {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String nom;
    private double prix;

   @ManyToMany
    @JoinTable(name = "compose")
    private List<Ticket> tickets;
}
