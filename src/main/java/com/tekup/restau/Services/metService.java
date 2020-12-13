package com.tekup.restau.Services;

import com.tekup.restau.models.Met;
import com.tekup.restau.models.Table;
import com.tekup.restau.reposotories.MetRep;
import com.tekup.restau.reposotories.TableRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class metService {

    private MetRep metRepo;

    @Autowired
    public metService(MetRep metRepo) {
        super();
        this.metRepo = metRepo;
    }

    public Met addMet(Met met){
        met.setNom(met.getNom().toUpperCase());
        return metRepo.save(met);
    }

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

    public List<Met> getAllMets(){
        return metRepo.findAll();
    }

    public String deleteMet(long id){
        Met met=searchById(id);
        metRepo.delete(met);
        return " Met supprimeé! ";
    }

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
