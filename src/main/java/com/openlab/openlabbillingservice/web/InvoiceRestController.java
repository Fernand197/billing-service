package com.openlab.openlabbillingservice.web;

import com.openlab.openlabbillingservice.dto.InvoiceRequestDTO;
import com.openlab.openlabbillingservice.dto.InvoiceResponseDTO;
import com.openlab.openlabbillingservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/customer/{customerId}")
    public List<InvoiceResponseDTO> getInvoiceByCustomerId(@PathVariable String customerId) {
        return invoiceService.invoiceByCustomerId(customerId);
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }
}
