package com.tekup.restau.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private Instant date;
    private int nbCouvert ;
    private double addition;

    @ManyToOne
    private Client client;

    @ManyToOne
    private  Table table;

    @ManyToMany
    @JoinTable(name = "compose")
    private List<Met>  mets;

    public List<Met> getMets() {
        return mets;
    }

    public void setMets(List<Met> mets) {
        this.mets = mets;
    }
}
