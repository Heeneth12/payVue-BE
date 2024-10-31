package com.example.payvuebe.invoice.service;

import com.example.payvuebe.invoice.dto.InvoiceDto;
import com.example.payvuebe.invoice.dto.InvoiceListDto;
import com.example.payvuebe.invoice.entity.InvoiceEntity;
import com.example.payvuebe.invoice.repository.InvoiceRepository;
import com.example.payvuebe.user.entity.UserEntity;
import com.example.payvuebe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Invoice Method
    public InvoiceEntity createInvoice(InvoiceDto invoiceDto) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();

        // Set createdBy user with provided UUID
        UserEntity createdBy = new UserEntity();
        createdBy.setId(invoiceDto.getCreatedBy());
        invoiceEntity.setCreatedBy(createdBy);

        // Set issuedTo user with provided UUID
        UserEntity issuedTo = new UserEntity();
        issuedTo.setId(invoiceDto.getIssuedTo());
        invoiceEntity.setIssuedTo(issuedTo);

        // Set other fields
        invoiceEntity.setCreatedAt(new Date());
        invoiceEntity.setStatus("pending");
        invoiceEntity.setTotalAmount(invoiceDto.getTotalAmount());
        invoiceEntity.setDescription(invoiceDto.getDescription());
        invoiceEntity.setStocks(invoiceDto.getProducts());

        // Save the invoice
        return invoiceRepository.save(invoiceEntity);
    }



    // Get Invoice by ID
    public InvoiceEntity getInvoiceById(String invoiceUuid) {
        InvoiceEntity  invoiceEntity = invoiceRepository.findById(invoiceUuid).orElse(null);
        return invoiceEntity;
    }

    // Update Invoice
    public InvoiceEntity updateInvoice(String invoiceUuid, InvoiceEntity updatedInvoice) {
        Optional<InvoiceEntity> existingInvoice = invoiceRepository.findById(invoiceUuid);
        if (existingInvoice.isPresent()) {
            InvoiceEntity invoice = existingInvoice.get();
            invoice.setIssuedTo(updatedInvoice.getIssuedTo());
            invoice.setStocks(updatedInvoice.getStocks());
            invoice.setTotalAmount(updatedInvoice.getTotalAmount());
            invoice.setDescription(updatedInvoice.getDescription());
            invoice.setStatus(updatedInvoice.getStatus());
            invoice.setUpdatedAt(new java.util.Date());  // Set the update date
            return invoiceRepository.save(invoice);
        } else {
            throw new RuntimeException("Invoice not found with UUID: " + invoiceUuid);
        }
    }

    // Get All Invoices
    public List<InvoiceListDto> getAllInvoices() {
        List<InvoiceEntity> invoiceEntities = invoiceRepository.findAll();
        List<InvoiceListDto> invoiceListDtos = new ArrayList<>();
        // Map entities to DTOs
        mapListToDto(invoiceListDtos, invoiceEntities);

        return invoiceListDtos;
    }

    private void mapListToDto(List<InvoiceListDto> invoiceListDtos, List<InvoiceEntity> invoiceEntities) {
        for (InvoiceEntity entity : invoiceEntities) {
            InvoiceListDto dto = new InvoiceListDto();
            dto.setInvoiceUuid(entity.getInvoiceUuid());
            dto.setCreatedBy(entity.getCreatedBy().getId()); // Assuming getUserId() returns Integer
            dto.setIssuedTo(entity.getIssuedTo().getId());   // Assuming getUserId() returns Integer
            dto.setTotalAmount(entity.getTotalAmount());
            dto.setBillDate(entity.getCreatedAt());
            dto.setStatus(entity.getStatus());
            invoiceListDtos.add(dto);
        }
    }

}
