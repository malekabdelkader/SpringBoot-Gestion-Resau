package com.tekup.restau.EndPoint;


import com.tekup.restau.DTO.ClientDTO.ClientRequest;
import com.tekup.restau.DTO.ClientDTO.ClientResponse;
import com.tekup.restau.Services.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private clientService clientservice;

    @Autowired
    public ClientController(clientService clientservice) {
        this.clientservice = clientservice;
    }

    @PostMapping()
    public ClientResponse addClient(@RequestBody ClientRequest client){
        return clientservice.addClient(client);
    }

    @GetMapping
    public List<ClientResponse> getAllTable(){
        return clientservice.getAllClients();
    }

    @PostMapping("/update/{id}")
    public ClientResponse updateTabel(@RequestBody ClientRequest client,@PathVariable("id")long id){
        return clientservice.modifierClient(id,client);
    }

    @GetMapping("/{id}")
    public  ClientResponse getByIdClient(@PathVariable("id") long id){
        return clientservice.searchById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return clientservice.deleteClient(id);
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
