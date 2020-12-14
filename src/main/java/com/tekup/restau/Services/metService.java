package com.tekup.restau.Services;

import com.tekup.restau.models.Dessert;
import com.tekup.restau.models.Entree;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Plat;
import com.tekup.restau.reposotories.MetsReposotories.DessertRep;
import com.tekup.restau.reposotories.MetsReposotories.EntreeRep;
import com.tekup.restau.reposotories.MetsReposotories.MetRep;
import com.tekup.restau.reposotories.MetsReposotories.PlatRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class metService {

    private MetRep metRepo;
    private PlatRep platRepo;
    private DessertRep dessertRepo;
    private EntreeRep entreeRepo;

    @Autowired
    public metService(MetRep metRepo, PlatRep platRepo, DessertRep dessertRepo, EntreeRep entreeRepo) {
        this.metRepo = metRepo;
        this.platRepo = platRepo;
        this.dessertRepo = dessertRepo;
        this.entreeRepo = entreeRepo;
    }

    //Ajout met
    public Met addPlat(Plat met){
        met.setNom(met.getNom().toUpperCase());
             return metRepo.save(met);
    }
    public Met addEntree(Entree met){
        met.setNom(met.getNom().toUpperCase());
        return metRepo.save(met);
    }
    public Met addDessert(Dessert met){
        met.setNom(met.getNom().toUpperCase());
        return metRepo.save(met);
    }

    //search
    public Met searchById(long id){
        Optional<Met> metOpt=metRepo.findById(id);
        Met met;
        if(metOpt.isPresent())
            met=metOpt.get();
        else
            throw new NoSuchElementException("Table avec ("+id+") introuvable ;");

        return met;

    }
    public Met getByNom(String nom){
        String functionNom=nom.toUpperCase();
        Optional<Met> opt=metRepo.findByNom(functionNom);
        Met met;
        if (opt.isPresent())
            met= opt.get();
        else
            throw new NoSuchElementException(("met avec nom ("+nom+") introuvable"));

        return met;
    }

    //get All
    public List<Met> getAllMets(){
        return metRepo.findAll();
    }
    public List<Plat> getAllPlats(){
        return platRepo.findAll();
    }
    public List<Dessert> getAllDesserts(){
        return dessertRepo.findAll();
    }
    public List<Entree> getAllEntrees(){
        return entreeRepo.findAll();
    }

    //delete
    public String deleteMet(long id){
        Met met=searchById(id);
        metRepo.delete(met);
        return " Met supprimeé! ";
    }

    //update
    public Met modifierMet(long id ,Met newMet){
        Met oldMet=searchById(id);

        if(newMet.getNom()!=null)
            oldMet.setNom(newMet.getNom().toUpperCase());

        if (newMet.getPrix() !=0)
            oldMet.setPrix(newMet.getPrix());
          metRepo.save(oldMet);

        return oldMet;
    }
}
