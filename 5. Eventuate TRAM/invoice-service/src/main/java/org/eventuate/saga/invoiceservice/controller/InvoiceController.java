package org.eventuate.saga.invoiceservice.controller;

import org.eventuate.saga.invoiceservice.model.Invoice;
import org.eventuate.saga.invoiceservice.model.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/invoices")
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<Invoice> getOne(@PathVariable String invoiceId) {
        Optional<Invoice> result = invoiceRepository.findById(invoiceId);
        return result != null ? ResponseEntity.ok(result.get()) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
