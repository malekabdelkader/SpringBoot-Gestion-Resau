package com.tekup.restau.Services;

import com.tekup.restau.models.Ticket;
import com.tekup.restau.reposotories.TicketRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ticketService {

    private TicketRep ticketRepo;

    @Autowired
    public ticketService(TicketRep ticketRepo) {
        super();
        this.ticketRepo = ticketRepo;
    }

    public Ticket addTicket(Ticket ticket){
        ticket.setDate(Instant.now());
        return ticketRepo.save(ticket);
    }

    public Ticket searchById(int num){
        Optional<Ticket> ticketOpt=ticketRepo.findById(num);
        Ticket ticket;
        if(ticketOpt.isPresent())
            ticket=ticketOpt.get();
        else
            throw new NoSuchElementException("ticket avec num ("+num+") introuvable ;");

        return ticket;

    }


    public List<Ticket> getAllTickets(){
        return ticketRepo.findAll();
    }

    public String deleteTicket(int num){
        Ticket ticket=searchById(num);
        ticketRepo.delete(ticket);
        return " Ticket supprimeé! ";
    }

    public Ticket modifierTicket(int num ,Ticket newTicket){
        Ticket oldTicket=searchById(num);

        if(newTicket.getAddition()!=0)
            oldTicket.setAddition(newTicket.getAddition());
//date prend la date de creation de ticket pas date de modification
/*        if (newTicket.getDate()!=null)
            oldTicket.setDate(newTicket.getDate());*/

      if (newTicket.getNbCouvert()!=0)
            oldTicket.setNbCouvert(newTicket.getNbCouvert());
        //valeur genereé automatique
   /*     if (newTicket.getNumero()!=0)
            oldTicket.setNumero(newTicket.getNumero());*/

        ticketRepo.save(oldTicket);

        return oldTicket;
    }
}
