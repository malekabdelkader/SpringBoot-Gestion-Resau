package com.tekup.restau.models;


import com.sun.javafx.beans.IDProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "tickets")
@ToString(exclude = "tickets")
@javax.persistence.Table(name = "myTable")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false)
    private int numero;
    private int nbCouvert;
    private String type;
    private double supplement;

    @OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;
}
