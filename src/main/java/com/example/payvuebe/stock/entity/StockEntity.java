package com.example.payvuebe.stock.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String stock_uuid;

    private String stock_name;

    private String stock_type;

    private BigDecimal stock_price;

    private Integer stock_unit;

    private BigDecimal stock_quantity;

    private Integer stock_unit_size;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updated_at;

}
