package com.tekup.restau.reposotories;

import com.tekup.restau.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRep  extends JpaRepository<Ticket,Integer> {
}
