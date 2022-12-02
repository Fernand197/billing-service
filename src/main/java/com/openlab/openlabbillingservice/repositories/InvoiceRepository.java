package com.openlab.openlabbillingservice.repositories;

import com.openlab.openlabbillingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    List<Invoice> findByCustomerId(String customerId);
}
