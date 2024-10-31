package com.example.payvuebe.invoice.entity;

import com.example.payvuebe.stock.entity.StockEntity;
import com.example.payvuebe.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String invoiceUuid;

    // Relationship to track the user who created the invoice
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserEntity createdBy;

    // Relationship to track the user who is the recipient of the invoice
    @ManyToOne
    @JoinColumn(name = "issued_to_user_id", nullable = false)
    private UserEntity issuedTo;

    // List of products in the invoice
    @ManyToMany
    @JoinTable(
            name = "invoice_stock",
            joinColumns = @JoinColumn(name = "invoice_uuid"),
            inverseJoinColumns = @JoinColumn(name = "stock_uuid")
    )
    private List<StockEntity> stocks;

    // Total amount for the invoice
    @Column(nullable = false)
    private BigDecimal totalAmount;

    // Short description or notes for the invoice
    private String description;

    // Timestamps for invoice creation and updates
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updatedAt;

    // Optional: Status of the invoice (e.g., Paid, Pending, etc.)
    private String status;
}
