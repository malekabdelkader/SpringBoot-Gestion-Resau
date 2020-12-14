package com.tekup.restau.DTO.TicketDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tekup.restau.DTO.ClientDTO.ClientRequest;
import com.tekup.restau.DTO.MetsDTO.MetRequest;
import com.tekup.restau.DTO.TableDTO.TableRequest;
import com.tekup.restau.models.Client;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Table;
import com.tekup.restau.reposotories.TableRep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
   private int numero;
    private Instant date;
    private int nbCouvert ;
    private double addition;

    private ClientRequest client;


   private TableRequest table;


    private List<MetRequest> mets;
}
