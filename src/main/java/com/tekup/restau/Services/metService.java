package com.tekup.restau.Services;

import com.tekup.restau.DTO.MetsDTO.MetRequest;
import com.tekup.restau.DTO.MetsDTO.MetResponse;
import com.tekup.restau.models.Dessert;
import com.tekup.restau.models.Entree;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Plat;
import com.tekup.restau.reposotories.MetsReposotories.DessertRep;
import com.tekup.restau.reposotories.MetsReposotories.EntreeRep;
import com.tekup.restau.reposotories.MetsReposotories.MetRep;
import com.tekup.restau.reposotories.MetsReposotories.PlatRep;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class metService {
    private ModelMapper mapper = new ModelMapper();
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
    public MetResponse addPlat(MetRequest metreq){
        Plat met=mapper.map(metreq,Plat.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapper.map(platRepo.save(met),MetResponse.class);
    }
    public MetResponse addEntree(MetRequest metreq){
        Entree met=mapper.map(metreq,Entree.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapper.map(entreeRepo.save(met),MetResponse.class);
    }
    public MetResponse addDessert(MetRequest metreq){
        Dessert met=mapper.map(metreq,Dessert.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapper.map(dessertRepo.save(met),MetResponse.class);
    }

    //search
    public MetResponse searchById(long id){
        Optional<Met> metOpt=metRepo.findById(id);
        Met met;
        if(metOpt.isPresent())
            met=metOpt.get();
        else
            throw new NoSuchElementException("Table avec ("+id+") introuvable ;");

        return mapper.map(met,MetResponse.class);

    }
    public MetResponse getByNom(String nom){
        String functionNom=nom.toUpperCase();
        Optional<Met> opt=metRepo.findByNom(functionNom);
        Met met;
        if (opt.isPresent())
            met= opt.get();
        else
            throw new NoSuchElementException(("met avec nom ("+nom+") introuvable"));
        return  mapper.map(met,MetResponse.class);
    }


    //get All
    public List<MetResponse> getAllMets(){
        List<MetResponse> metsResp=new ArrayList<>();
              List<Met> mets= metRepo.findAll();
              for (Met met:mets){
                  metsResp.add(mapper.map(met,MetResponse.class));
              }
        return metsResp;
    }

    public List<MetResponse> getAllPlats(){
        List<MetResponse> metsResp=new ArrayList<>();
        List<Plat> plats= platRepo.findAll();
        for (Plat met:plats){
            metsResp.add(mapper.map(met,MetResponse.class));
        }
        return metsResp;
    }
    public List<MetResponse> getAllDesserts(){
        List<MetResponse> metsResp=new ArrayList<>();
        List<Dessert> desserts= dessertRepo.findAll();
        for (Dessert met:desserts){
            metsResp.add(mapper.map(met,MetResponse.class));
        }
        return metsResp;
    }
    public List<MetResponse> getAllEntrees(){
        List<MetResponse> metsResp=new ArrayList<>();
        List<Entree> entrees= entreeRepo.findAll();
        for (Entree met:entrees){
            metsResp.add(mapper.map(met,MetResponse.class));
        }
        return metsResp;
    }

    //delete
    public String deleteMet(long id){
        searchById(id);
        metRepo.deleteById(id);
        return " Met supprimeé! ";
    }

    //update
    public MetResponse modifierMet(long id , MetRequest newMetReq){
        searchById(id);
               Met newMet=mapper.map(newMetReq,Met.class);
               Optional<Met> opt=metRepo.findById(id);
               Met oldMet=null;
               if(opt.isPresent()){
                   oldMet= opt.get();
               }

        if(newMet.getNom()!=null)
            oldMet.setNom(newMet.getNom().toUpperCase().trim());

        if (newMet.getPrix() !=0)
            oldMet.setPrix(newMet.getPrix());
          metRepo.save(oldMet);

        return mapper.map(oldMet,MetResponse.class);
    }
}
