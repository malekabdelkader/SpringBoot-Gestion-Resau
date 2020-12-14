package com.tekup.restau.reposotories.MetsReposotories;

import com.tekup.restau.models.Dessert;
import com.tekup.restau.models.Entree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRep extends JpaRepository<Dessert,Long> {
}
