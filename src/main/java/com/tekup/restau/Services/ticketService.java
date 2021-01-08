package com.tekup.restau.Services;

import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.DTO.TicketDTO.TicketResponse;
import com.tekup.restau.models.Client;
import com.tekup.restau.models.Ticket;
import com.tekup.restau.reposotories.ClientRepo;
import com.tekup.restau.reposotories.TicketRep;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ticketService {

    private TicketRep ticketRepo;
    private  ClientRepo clientRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public ticketService(TicketRep ticketRepo) {
        super();
        this.ticketRepo = ticketRepo;
    }
    public Ticket addTicket(Ticket ticket){
        ticket.setDate(Instant.now());
        Ticket ticketInBase=ticketRepo.save(ticket);
        return ticket;

    }
    /*public TicketResponse addTicket(TicketRequest ticketreq){
        Ticket ticket=mapper.map(ticketreq,Ticket.class);
        ticket.setDate(Instant.now());
        Ticket ticketInBase=ticketRepo.save(ticket);
        return mapper.map(ticketInBase,TicketResponse.class);

    }*/

    public TicketResponse searchById(int num){
        Optional<Ticket> ticketOpt=ticketRepo.findById(num);
        Ticket ticket;
        if(ticketOpt.isPresent())
            ticket=ticketOpt.get();
        else
            throw new NoSuchElementException("ticket avec num ("+num+") introuvable ;");

        return mapper.map(ticket,TicketResponse.class);

    }


    public List<Ticket> getAllTickets(){

        List<Ticket> tickets=ticketRepo.findAll();
      /*
      List<TicketResponse> ticketsResp=new ArrayList<>();
      for (Ticket ticket:tickets){
            ticketsResp.add(mapper.map(ticket,TicketResponse.class));
        }
        return ticketsResp;
        */
        return  tickets;
    }

    public String deleteTicket(int num){
        searchById(num);
        ticketRepo.deleteById(num);
        return " Ticket supprimeé! ";
    }

    public TicketResponse modifierTicket(int num ,TicketRequest ticketreq){
        Ticket newTicket=mapper.map(ticketreq,Ticket.class);
        searchById(num);
        Optional<Ticket> opt=ticketRepo.findById(num);

        Ticket oldTicket= opt.isPresent()?opt.get():null;

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

        return mapper.map(oldTicket,TicketResponse.class);
    }
}
