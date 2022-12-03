package com.openlab.openlabbillingservice.services;

import com.openlab.openlabbillingservice.dto.InvoiceRequestDTO;
import com.openlab.openlabbillingservice.dto.InvoiceResponseDTO;
import com.openlab.openlabbillingservice.entities.Customer;
import com.openlab.openlabbillingservice.entities.Invoice;
import com.openlab.openlabbillingservice.mappers.InvoiceMapper;
import com.openlab.openlabbillingservice.openfeign.CustomerRestClient;
import com.openlab.openlabbillingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);

        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoiceByCustomerId(String customerId) {

        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);

        return invoices.stream().map(invoice -> {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
            return invoiceMapper.fromInvoice(invoice);
        }).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        return invoiceRepository.findAll().stream().map(invoice -> {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
            return invoiceMapper.fromInvoice(invoice);
        }).collect(Collectors.toList());
    }
}
