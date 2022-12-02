package com.openlab.openlabbillingservice;

import com.openlab.openlabbillingservice.dto.InvoiceRequestDTO;
import com.openlab.openlabbillingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class OpenlabBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenlabBillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(75000), "CO1"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(5000), "CO1"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(77800), "CO2"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(7800), "CO2"));
        };
    }

}
