package com.tekup.restau.EndPoint;


import com.tekup.restau.Services.metService;
import com.tekup.restau.models.Met;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/met")
public class MetController {
    private metService metservice;

    @Autowired
    public MetController(metService metservice) {
        this.metservice = metservice;
    }

    @PostMapping()
    public Met addMet(@RequestBody Met met){
        return metservice.addMet(met);
    }

    @GetMapping
    public List<Met> getAllTable(){
        return metservice.getAllMets();
    }

    @PostMapping("/update/{id}")
    public Met updateTabel(@RequestBody Met met,@PathVariable("id")long id){
        return metservice.modifierMet(id,met);
    }

    @GetMapping("/{id}")
    public  Met getByIdMet(@PathVariable("id") long id){
        return metservice.searchById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return metservice.deleteMet(id);
    }

    @GetMapping("/nom/{nom}")
    public  Met getByNomMet(@PathVariable("nom") String nom){
        return metservice.getByNom(nom);
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
