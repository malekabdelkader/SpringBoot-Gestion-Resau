package com.tekup.restau.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String nom;
    private String prenom;

    private LocalDate dateDeNaissance;

    private  String courriel;
    private  String telephone;

    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;
}
