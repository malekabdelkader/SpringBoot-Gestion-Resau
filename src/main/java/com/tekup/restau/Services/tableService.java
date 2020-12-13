package com.tekup.restau.Services;

import com.tekup.restau.models.Table;
import com.tekup.restau.reposotories.TableRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class tableService {

    private TableRep tableRepo;

    @Autowired
    public tableService(TableRep tableRepo) {
        super();
        this.tableRepo = tableRepo;
    }

    public Table addTable(Table table){
        return tableRepo.save(table);
    }

    public Table searchById(long id){
        Optional<Table> tableOpt=tableRepo.findById(id);
        Table table;
        if(tableOpt.isPresent())
            table=tableOpt.get();
        else
            throw new NoSuchElementException("Table avec Id introuvable");

        return table;

    }

    public List<Table> getAllTables(){
        return tableRepo.findAll();
    }

    public String deleteTable(long id){
        Table table=searchById(id);
        tableRepo.delete(table);
        return "Table supprimée avec succes!";
    }

    public Table getbyNumero(int num){
        Optional<Table> opt=  tableRepo.findByNumero(num);
        Table table;
        if(opt.isPresent())
            table=opt.get();
        else
            throw new NoSuchElementException("table with this num "+num+" intractable!  ;");
        return table;
    }

    public Table modifierTable(long id ,Table newTable){
        Table oldtable=searchById(id);

        if(newTable.getNbCouvert()!=0)
            oldtable.setNbCouvert(newTable.getNbCouvert());

        if (newTable.getNumero() !=0)
            oldtable.setNumero(newTable.getNumero());

        if(newTable.getType()!=null)
            oldtable.setType(newTable.getType());

        if (newTable.getSupplement()!=0)
            oldtable.setSupplement(newTable.getSupplement());
        tableRepo.save(oldtable);

        return oldtable;
    }
}
