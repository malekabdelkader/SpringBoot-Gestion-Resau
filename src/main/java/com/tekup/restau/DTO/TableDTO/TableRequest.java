package com.tekup.restau.DTO.TableDTO;

import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.models.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
public class TableRequest {

    private long id;
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
    /*private List<TicketRequest>  tickets;*/
}
