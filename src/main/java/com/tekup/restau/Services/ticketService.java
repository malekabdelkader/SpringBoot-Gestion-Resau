package com.tekup.restau.Services;

import com.tekup.restau.DTO.MetsDTO.MetResponse;
import com.tekup.restau.DTO.TableDTO.TableResponse;
import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.DTO.TicketDTO.TicketResponse;
import com.tekup.restau.models.*;
import com.tekup.restau.reposotories.ClientRepo;
import com.tekup.restau.reposotories.MetsReposotories.MetRep;
import com.tekup.restau.reposotories.TableRep;
import com.tekup.restau.reposotories.TicketRep;
import javafx.scene.control.Tab;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ticketService {
    private clientService clientservice;
    private tableService tableservice;
    private TicketRep ticketRepo;
    private  ClientRepo clientRepo;
    private TableRep tableRep;
    private MetRep metRepo;
    private ModelMapper mapper = new ModelMapper();
    @Autowired
    public ticketService(TicketRep ticketRepo, ClientRepo clientRepo, MetRep metRepo,TableRep tableRep) {
        super();
        this.ticketRepo = ticketRepo;
        this.clientRepo = clientRepo;
        this.metRepo = metRepo;
        this.tableRep =tableRep;
        this.clientservice=new clientService(clientRepo);
        this.tableservice=new tableService(tableRep);
    }

    public TableResponse mostReservedTable(){
        Map<Long,Integer> listTableWithkey=new HashMap<>();
        List<Table> tables=tableRep.findAll();
        for(Table table:tables){
            listTableWithkey.put(table.getId(),table.getTickets().size());
        }
        Long toptable= listTableWithkey.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        Table table=tableRep.findById(toptable).get();
        return mapper.map(table,TableResponse.class);
       }
       public String RevenueDerniere(){
        List<Ticket> tickets=ticketRepo.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (Ticket ticket:tickets){
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(30)))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(7)))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(1)))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n Revenue jour derniere :"+revenueJours;
       }


    public MetResponse mostBuyedPlat(Instant begin,Instant end){
        List<Ticket> tickets=ticketRepo.findAll();
        List<Long> idList=new ArrayList<>();
        for (Ticket ticket:tickets){
            //check if ticket is in the given time interval
            if(ticket.getDate().isAfter(begin)&&ticket.getDate().isBefore(end)){

                for (Met met:ticket.getMets()){
                    //filtering Plat out from list of mets
                    if(met instanceof Plat){
                        idList.add(met.getId());
                    }
                }
            }
        }
                Long metid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        Met met=metRepo.findById(metid).get();
        return mapper.map(met,MetResponse.class);
    }


    public Ticket addTicket(Ticket ticket){
        ticket.setDate(Instant.now());
        ticket.setAddition(0);
        List<Met>  mets= ticket.getMets();
        this.tableservice.searchById(ticket.getTable().getId());
        this.clientservice.searchById(ticket.getClient().getId());
        for (Met met:mets){
          Optional<Met> opt=metRepo.findById(met.getId());
          if(opt.isPresent()){
              ticket.setAddition(ticket.getAddition()+opt.get().getPrix());
          }else {
              throw new NoSuchElementException("Met avec id :"+met.getId()+" introuvable");
          }
        }
        Ticket ticketInBase=ticketRepo.save(ticket);
        return ticketInBase;
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
