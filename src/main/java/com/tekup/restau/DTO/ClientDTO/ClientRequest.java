package com.tekup.restau.DTO.ClientDTO;

import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private long id;

    private  String nom;
    private String prenom;

    private LocalDate dateDeNaissance;

    private  String courriel;
    private  String telephone;


    private List<TicketRequest> tickets;
}
