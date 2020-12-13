package com.tekup.restau.reposotories;

import com.tekup.restau.models.Met;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetRep extends JpaRepository<Met,Long> {
    public Optional<Met> findByNom(String nom);
}
