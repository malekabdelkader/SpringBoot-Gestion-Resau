package com.tekup.restau.Services;

import com.tekup.restau.DTO.ClientDTO.ClientRequest;
import com.tekup.restau.DTO.ClientDTO.ClientResponse;
import com.tekup.restau.models.Client;
import com.tekup.restau.reposotories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class clientService {

    private ClientRepo clientRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public clientService(ClientRepo clientRepo) {
        super();
        this.clientRepo = clientRepo;
    }

    public ClientResponse addClient(ClientRequest clientRequest){
        Client client=mapper.map(clientRequest,Client.class);
        Client clinetInDb=clientRepo.save(client);

        return mapper.map(clinetInDb,ClientResponse.class);
    }

    public ClientResponse searchById(long id){
        Optional<Client> clientOpt=clientRepo.findById(id);
        Client client;
        if(clientOpt.isPresent())
            client=clientOpt.get();
        else
            throw new NoSuchElementException("client avec ("+id+") introuvable ;");

        return mapper.map(client, ClientResponse.class);

    }


    public List<ClientResponse> getAllClients(){
        List<Client> clients=clientRepo.findAll();
        List<ClientResponse> reponses=new ArrayList<>();

        for (Client client:clients) {
            reponses.add(mapper.map(client,ClientResponse.class));
        }

        return reponses;

    }

    public String deleteClient(long id){
        ClientResponse clientResponse=searchById(id);
        Client client=mapper.map(clientResponse,Client.class);
        clientRepo.delete(client);
        return " Client supprimeé! ";
    }

    public ClientResponse modifierClient(long id , ClientRequest clientRequest){
        //PersonEntity personRequest = mapper.map(request, PersonEntity.class);
        Client newClient=mapper.map(clientRequest,Client.class);
        ClientResponse clientResponse=searchById(id);

        Client oldClient=mapper.map(clientResponse,Client.class);

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

       Client savedClient= clientRepo.save(oldClient);

        return mapper.map(savedClient,ClientResponse.class);
    }
}
