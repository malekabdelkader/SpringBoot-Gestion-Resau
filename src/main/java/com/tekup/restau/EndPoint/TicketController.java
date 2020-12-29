package com.tekup.restau.EndPoint;


import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.DTO.TicketDTO.TicketResponse;
import com.tekup.restau.Services.ticketService;
import com.tekup.restau.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/ticket")
public class TicketController {
    private ticketService ticketserv;

    @Autowired
    public TicketController(ticketService ticketserv) {
        this.ticketserv = ticketserv;
    }



    @PostMapping()
    public TicketResponse addTicket(@RequestBody TicketRequest ticket){
        return ticketserv.addTicket(ticket);
    }

    @GetMapping
    public List<TicketResponse> getAllTicket(){
        return ticketserv.getAllTickets();
    }

    @PostMapping("/update/{num}")
    public TicketResponse updateTabel(@RequestBody TicketRequest ticket, @PathVariable("num")int num){
        return ticketserv.modifierTicket(num,ticket);
    }



    @GetMapping("/{num}")
    public  TicketResponse getByIdTicket(@PathVariable("num") int num){
        return ticketserv.searchById(num);
    }

    @DeleteMapping("/{num}")
    public String deleteByIdTicket(@PathVariable("num") int num){
        return ticketserv.deleteTicket(num);
    }

    //catch Errors and Exceptions
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // To Return 1 validation error
        //return new ResponseEntity<String>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }



}
