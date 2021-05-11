package com.example.RetailStore.repository;

import com.example.RetailStore.model.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailsRepository extends JpaRepository<BillDetails,Long> {
}
