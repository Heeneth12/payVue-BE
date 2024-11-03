package com.example.payvuebe.stock.service;

import com.example.payvuebe.stock.dto.StockDTO;
import com.example.payvuebe.stock.entity.StockEntity;
import com.example.payvuebe.stock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    // Create or Update Stock
    @Transactional
    public StockEntity addStock(StockDTO stockDTO) {
        LOGGER.info("Saving stock: {}", stockDTO);
        try {
            LOGGER.info("Saving stock: {}", stockDTO);
            StockEntity stockEntity = mapDtoToEntity(stockDTO);
            return stockRepository.save(stockEntity);

        } catch (Exception e) {
            LOGGER.error("Error occurred while saving stock: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save stock. Please try again later.");
        }
    }

    private StockEntity mapDtoToEntity(StockDTO stockDTO) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setStock_uuid(stockDTO.getStock_uuid());
        stockEntity.setStock_name(stockDTO.getStock_name());
        stockEntity.setStock_type(stockDTO.getStock_type());
        stockEntity.setStock_price(stockDTO.getStock_price());
        stockEntity.setStock_unit(stockDTO.getStock_unit());
        stockEntity.setStock_quantity(stockDTO.getStock_quantity());
        stockEntity.setStock_unit_size(stockDTO.getStock_unit_size());
        stockEntity.setUpdated_at(new Date());
        return stockEntity;
    }

    // Get All Stocks
    public List<StockEntity> getAllStocks() {
        LOGGER.info("Retrieving all stocks");
        return stockRepository.findAll();
    }

    // Get Stock by ID
    public StockEntity getStockById(String id) {
        LOGGER.info("Retrieving stock with id: {}", id);
        Optional<StockEntity> stock = stockRepository.findById(id);
        StockEntity stockEntity = new StockEntity() {
        };
        if (stock.isPresent()) {
            return stock.get();

        }
        return stockEntity;
    }

    // Delete Stock by ID
    @Transactional
    public void deleteStockById(String id) {
        LOGGER.info("Deleting stock with id: {}", id);
        stockRepository.deleteById(id);
    }


    public StockEntity updateStock(StockDTO stockDTO) {
        LOGGER.info("Updating stock: {}", stockDTO);

        try {
            // Retrieve existing stock entity
            StockEntity stockEntity = getStockById(stockDTO.getStock_uuid());
            // Update fields with values from the DTO
            stockEntity.setStock_name(stockDTO.getStock_name());
            stockEntity.setStock_type(stockDTO.getStock_type());
            stockEntity.setStock_price(stockDTO.getStock_price());
            stockEntity.setStock_unit(stockDTO.getStock_unit());
            stockEntity.setStock_quantity(stockDTO.getStock_quantity());
            stockEntity.setStock_unit_size(stockDTO.getStock_unit_size());
            stockEntity.setUpdated_at(new Date());

            // Save updated entity and return
            return stockRepository.save(stockEntity);

        } catch (Exception e) {
            LOGGER.error("Error updating stock with UUID {}: {}", stockDTO.getStock_uuid(), e.getMessage(), e);
            throw new RuntimeException("Failed to update stock. Please try again later.");
        }
    }

}
