package com.example.payvuebe.invoice.repository;

import com.example.payvuebe.invoice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,String> {
}
