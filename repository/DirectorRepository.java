package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;



public interface DirectorRepository extends JpaRepository<Director, UUID> {
    // Additional custom queries can be added here if needed
}