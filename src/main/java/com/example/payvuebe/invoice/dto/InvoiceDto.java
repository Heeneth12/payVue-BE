package com.example.payvuebe.invoice.dto;

import com.example.payvuebe.stock.entity.StockEntity;
import com.example.payvuebe.user.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InvoiceDto {

    private Integer createdBy;

    private Integer  issuedTo;

    private List<StockEntity> products;

    private BigDecimal totalAmount;

    private String description;

    private Boolean addDiscount;

    private String status;

}
