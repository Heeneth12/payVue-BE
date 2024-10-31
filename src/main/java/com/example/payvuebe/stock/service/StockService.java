package com.example.payvuebe.stock.service;

import com.example.payvuebe.stock.entity.StockEntity;
import com.example.payvuebe.stock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    // Create or Update Stock
    @Transactional
    public StockEntity addStock(StockEntity stock) {
        LOGGER.info("Saving stock: {}", stock);
        return stockRepository.save(stock);
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
}
