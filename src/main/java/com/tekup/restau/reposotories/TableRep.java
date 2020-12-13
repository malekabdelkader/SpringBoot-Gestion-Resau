package com.tekup.restau.reposotories;

import com.tekup.restau.models.Met;
import com.tekup.restau.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRep extends JpaRepository<Table,Long> {
    public Optional<Table> findByNumero(int numero);
}
