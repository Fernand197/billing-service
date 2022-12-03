package com.openlab.openlabbillingservice.services;

import com.openlab.openlabbillingservice.dto.InvoiceRequestDTO;
import com.openlab.openlabbillingservice.dto.InvoiceResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface InvoiceService {
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    public InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoiceByCustomerId(String customerId);

    List<InvoiceResponseDTO> allInvoices();
}
