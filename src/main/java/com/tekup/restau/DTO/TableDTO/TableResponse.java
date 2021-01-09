package com.tekup.restau.DTO.TableDTO;

import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.models.Ticket;
import lombok.*;

import java.util.List;

@Data
public class TableResponse {
    private long id;
    private int numero;
    private int nbCouvert;
    @Getter
    @Setter
    private String type;

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


    @Getter
    @Setter
    private double supplement;
   /* private String tickets;
    public String getTickets() {
        return tickets;
    }
    public void setTickets(String  tickets) {
        this.tickets = tickets;
    }*/

}
