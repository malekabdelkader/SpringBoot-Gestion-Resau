package com.tekup.restau.Services;

import com.tekup.restau.models.Client;
import com.tekup.restau.reposotories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class clientService {

    private ClientRepo clientRepo;

    @Autowired
    public clientService(ClientRepo clientRepo) {
        super();
        this.clientRepo = clientRepo;
    }

    public Client addClient(Client client){
        return clientRepo.save(client);
    }

    public Client searchById(long id){
        Optional<Client> clientOpt=clientRepo.findById(id);
        Client client;
        if(clientOpt.isPresent())
            client=clientOpt.get();
        else
            throw new NoSuchElementException("client avec ("+id+") introuvable ;");

        return client;

    }


    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }

    public String deleteClient(long id){
        Client client=searchById(id);
        clientRepo.delete(client);
        return " Client supprimeé! ";
    }

    public Client modifierClient(long id ,Client newClient){
        Client oldClient=searchById(id);

        if(newClient.getCourriel()!=null)
            oldClient.setCourriel(newClient.getCourriel());

        if (newClient.getDateDeNaissance() !=null)
            oldClient.setDateDeNaissance(newClient.getDateDeNaissance());

        if (newClient.getNom()!=null)
            oldClient.setNom(newClient.getNom());

        if (newClient.getPrenom()!=null)
            oldClient.setPrenom(newClient.getPrenom());

        if (newClient.getTelephone()!=null)
            oldClient.setTelephone(newClient.getTelephone());



        clientRepo.save(oldClient);

        return oldClient;
    }
}
