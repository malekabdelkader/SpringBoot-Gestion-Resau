package com.tekup.restau.EndPoint;


import com.tekup.restau.Services.metService;
import com.tekup.restau.models.Dessert;
import com.tekup.restau.models.Entree;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Plat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/met/")
public class MetController {
    private metService metservice;

    @Autowired
    public MetController(metService metservice) {
        this.metservice = metservice;
    }

    //Ajouter met selon type
    @PostMapping("/plat")
    public Met addPlat(@RequestBody Plat met,String type){
        return metservice.addPlat(met);
    }

    @PostMapping("/dessert")
    public Met addPlat(@RequestBody Dessert met, String type){
        return metservice.addDessert(met);
    }

    @PostMapping("/entree")
    public Met addPlat(@RequestBody Entree met, String type){
        return metservice.addEntree(met);
    }

    //get selon met type
    @GetMapping
    public List<Met> getAllMets(){
        return metservice.getAllMets();
    }

    @GetMapping("/plat")
    public List<Plat> getAllPlats(){
        return metservice.getAllPlats();
    }

    @GetMapping("/dessert")
    public List<Dessert> getAllDesserts(){
        return metservice.getAllDesserts();
    }

    @GetMapping("/entree")
    public List<Entree> getAllEntrees(){
        return metservice.getAllEntrees();
    }

    //update
    @PostMapping("/update/{id}")
    public Met updateTabel(@RequestBody Met met,@PathVariable("id")long id){
        return metservice.modifierMet(id,met);
    }
    //get by ID
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
