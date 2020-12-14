package com.tekup.restau.reposotories.MetsReposotories;

import com.tekup.restau.models.Entree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntreeRep extends JpaRepository<Entree,Long> {
}
