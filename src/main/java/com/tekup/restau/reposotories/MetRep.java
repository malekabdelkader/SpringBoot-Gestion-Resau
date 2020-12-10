package com.tekup.restau.reposotories;

import com.tekup.restau.models.Mets.Met;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetRep extends JpaRepository<Met,String> {
}
