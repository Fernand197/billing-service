package com.openlab.openlabbillingservice.mappers;

import com.openlab.openlabbillingservice.dto.InvoiceRequestDTO;
import com.openlab.openlabbillingservice.dto.InvoiceResponseDTO;
import com.openlab.openlabbillingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    // map invoice request to invoice objet
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);

    // Map invoice objet to invoice response
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
