package com.tekup.restau.reposotories;

import com.tekup.restau.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo  extends JpaRepository<Client,Long> {
}
