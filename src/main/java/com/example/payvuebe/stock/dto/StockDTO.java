package com.example.payvuebe.stock.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockDTO {

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
