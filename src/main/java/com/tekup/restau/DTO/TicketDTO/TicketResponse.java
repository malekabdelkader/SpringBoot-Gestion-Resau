package com.tekup.restau.DTO.TicketDTO;

import com.tekup.restau.models.Client;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private int numero;
    private Instant date;
    private int nbCouvert ;
    private double addition;
    private String client;
    private String table;
    private String mets;
}