package com.example.RetailStore.repository;

import com.example.RetailStore.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill,Long> {
    @Override
    Optional<Bill> findById(Long id);
}
