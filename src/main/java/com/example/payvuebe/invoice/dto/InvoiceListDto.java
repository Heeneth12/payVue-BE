package com.example.payvuebe.invoice.dto;

import com.example.payvuebe.stock.entity.StockEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceListDto {

    private String invoiceUuid;

    private Integer createdBy;

    private Integer  issuedTo;

    private BigDecimal totalAmount;

    private Date billDate;

    private String status;

}
