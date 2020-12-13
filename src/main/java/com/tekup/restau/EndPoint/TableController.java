package com.tekup.restau.EndPoint;


import com.tekup.restau.Services.metService;
import com.tekup.restau.Services.tableService;
import com.tekup.restau.models.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/table")
public class TableController {
    private tableService tableserv;

    @Autowired
    public TableController(tableService tableserv) {
        this.tableserv = tableserv;
    }



     @PostMapping()
    public Table addTable(@RequestBody Table table){
        return tableserv.addTable(table);
     }

     @GetMapping
    public List<Table> getAllTable(){
        return tableserv.getAllTables();
     }

     @PostMapping("/update/{id}")
    public Table updateTabel(@RequestBody Table table,@PathVariable("id")long id){
        return tableserv.modifierTable(id,table);
     }

    @GetMapping("/numero/{id}")
    public  Table getByNumTable(@PathVariable("id") int num){
        return tableserv.getbyNumero(num);
    }

     @GetMapping("/{id}")
    public  Table getByIdTable(@PathVariable("id") long id){
        return tableserv.searchById(id);
     }

     @DeleteMapping("/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return tableserv.deleteTable(id);
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
