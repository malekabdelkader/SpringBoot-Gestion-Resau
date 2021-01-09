package com.tekup.restau.Services;

import com.tekup.restau.DTO.TableDTO.TableRequest;
import com.tekup.restau.DTO.TableDTO.TableResponse;
import com.tekup.restau.DTO.TicketDTO.TicketRequest;
import com.tekup.restau.models.Table;
import com.tekup.restau.reposotories.TableRep;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class tableService {

    private TableRep tableRepo;
    private ModelMapper mapper = new ModelMapper();
    @Autowired
    public tableService(TableRep tableRepo) {
        super();
        this.tableRepo = tableRepo;
    }

    public TableResponse addTable(TableRequest tableReq){
        Table table=mapper.map(tableReq,Table.class);

        Table tableDb= tableRepo.save(table);
        return mapper.map(tableDb,TableResponse.class);
    }

    public TableResponse searchById(long id){
        Optional<Table> tableOpt=tableRepo.findById(id);
        Table table;
        if(tableOpt.isPresent())
            table=tableOpt.get();
        else
            throw new NoSuchElementException("Table avec Id introuvable");

        return mapper.map(table,TableResponse.class);

    }

    public List<TableResponse> getAllTables(){
         List<Table> tables=  tableRepo.findAll();
        List<TableResponse> response=new ArrayList<>();
        for (Table table:tables) {
            response.add(mapper.map(table,TableResponse.class));
        }
        return response;

    }

    public String deleteTable(long id){
        TableResponse table=searchById(id);
        tableRepo.deleteById(id);
        return "Table supprimée avec succes!";
    }

    public TableResponse getbyNumero(int num){
        Optional<Table> opt=  tableRepo.findByNumero(num);
        Table table;
        if(opt.isPresent())
            table=opt.get();
        else
            throw new NoSuchElementException("table with this num "+num+" intractable!  ;");
        return mapper.map(table,TableResponse.class);
    }

    public TableResponse modifierTable(long id ,TableRequest newTableReq){
        TableResponse tableResponse=searchById(id);
        Table oldtable=mapper.map(tableResponse,Table.class);
        Table newTable=mapper.map(newTableReq,Table.class);

        if(newTable.getNbCouvert()!=0)
            oldtable.setNbCouvert(newTable.getNbCouvert());

        if (newTable.getNumero() !=0)
            oldtable.setNumero(newTable.getNumero());

        if(newTable.getType()!=null)
            oldtable.setType(newTable.getType());

        if (newTable.getSupplement()!=0)
            oldtable.setSupplement(newTable.getSupplement());

             Table TableInDb= tableRepo.save(oldtable);

        return mapper.map(TableInDb,TableResponse.class);
    }
}
