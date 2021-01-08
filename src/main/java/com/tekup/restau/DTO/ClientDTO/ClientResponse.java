package com.tekup.restau.DTO.ClientDTO;

import com.tekup.restau.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private long id;

    private  String nom;
    private String prenom;

    private LocalDate dateDeNaissance;

    private  String courriel;
    private  String telephone;

   /* private String tickets;*/
}
