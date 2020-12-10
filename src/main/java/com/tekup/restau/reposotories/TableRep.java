package com.tekup.restau.reposotories;

import com.tekup.restau.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRep extends JpaRepository<Table,Integer> {
}
