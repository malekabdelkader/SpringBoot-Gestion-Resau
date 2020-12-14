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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSupplement() {
        return supplement;
    }

    public void setSupplement(double supplement) {
        this.supplement = supplement;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;
}
