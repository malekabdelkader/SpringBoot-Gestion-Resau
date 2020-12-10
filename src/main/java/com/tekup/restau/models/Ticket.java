package com.tekup.restau.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    private int numero;
    private LocalDateTime date;
    private int nbCouvert ;
    private double addition;

    @ManyToOne
    private Client client;

    @ManyToOne
    private  Table table;

    @ManyToMany(mappedBy = "tickets",cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinTable(name = "compose")
    private List<Met>  mets;

}
