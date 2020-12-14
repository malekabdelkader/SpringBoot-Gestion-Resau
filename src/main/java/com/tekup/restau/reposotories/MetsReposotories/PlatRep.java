package com.tekup.restau.reposotories.MetsReposotories;

import com.tekup.restau.models.Entree;
import com.tekup.restau.models.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatRep extends JpaRepository<Plat,Long> {
}
