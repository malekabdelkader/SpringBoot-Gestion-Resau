package com.tekup.restau.DTO.MetsDTO;

import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetRequest {
    private long id;

    private String nom;
    private double prix;

    private List<TicketRequest> tickets;
}
