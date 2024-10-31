package com.example.payvuebe.invoice.controller;

import com.example.payvuebe.invoice.dto.InvoiceDto;
import com.example.payvuebe.invoice.dto.InvoiceListDto;
import com.example.payvuebe.invoice.entity.InvoiceEntity;
import com.example.payvuebe.invoice.service.InvoiceService;
import com.example.payvuebe.utils.ResponseResource;
import com.example.payvuebe.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Create Invoice
    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<InvoiceEntity> createInvoice(@RequestBody InvoiceDto invoice) {
        InvoiceEntity createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, createdInvoice,
                Status.SUCCESS);
    }

    // Get Invoice by ID
    @GetMapping(value = "/{invoiceUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<InvoiceEntity> getInvoiceById(@PathVariable String invoiceUuid) {
        InvoiceEntity invoice = invoiceService.getInvoiceById(invoiceUuid);
        return new ResponseResource<>(ResponseResource.R_CODE_OK ,ResponseResource.RES_SUCCESS , invoice,
                Status.SUCCESS);
    }

    // Update Invoice
    @PutMapping(value = "/update/{invoiceUuid}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceEntity> updateInvoice(
            @PathVariable String invoiceUuid,
            @RequestBody InvoiceEntity updatedInvoice) {
        try {
            InvoiceEntity invoice = invoiceService.updateInvoice(invoiceUuid, updatedInvoice);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get All Invoices
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<List<InvoiceListDto>> getAllInvoices() {
        List<InvoiceListDto> invoices = invoiceService.getAllInvoices();
        return new ResponseResource<>(ResponseResource.R_CODE_OK ,ResponseResource.RES_SUCCESS , invoices,
                Status.SUCCESS);
    }
}
