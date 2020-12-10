package com.tekup.restau.models;


import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    @Id
    private int numero;
    private int nbCouvert;
    private String type;
    private double supplement;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;
}
