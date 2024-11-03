package com.example.payvuebe.stock.controller;

import com.example.payvuebe.stock.dto.StockDTO;
import com.example.payvuebe.stock.entity.StockEntity;
import com.example.payvuebe.stock.service.StockService;
import com.example.payvuebe.utils.ResponseResource;
import com.example.payvuebe.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;


    /**
     * addStock
     *
     * @param stock
     * @return
     */
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<StockEntity> addStock(@RequestBody StockDTO stock) {
        LOGGER.info("Request to save/update stock: {}", stock);
        StockEntity savedStock = stockService.addStock(stock);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, savedStock,
                Status.SUCCESS);
    }

    @PostMapping(value = "/update" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<StockEntity> updateStock(@RequestBody StockDTO stockDTO) {
        LOGGER.info("Request to update stock: {}", stockDTO);
        StockEntity updatedStock = stockService.updateStock(stockDTO);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, updatedStock,
                Status.SUCCESS);
    }

    /**
     * @return
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<List<StockEntity>> getAllStocks() {
        LOGGER.info("Request to retrieve all stocks");
        List<StockEntity> stocks = stockService.getAllStocks();
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, stocks, Status.SUCCESS);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<StockEntity> getStockById(@PathVariable String id) {
        LOGGER.info("Request to retrieve stock with id: {}", id);
        StockEntity stock = stockService.getStockById(id);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, stock, Status.SUCCESS);
    }

    // Delete Stock by ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<String> deleteStockById(@PathVariable String id) {
        LOGGER.info("Request to delete stock with id: {}", id);
        stockService.deleteStockById(id);
        String message = "Stock with id " + id + " has been deleted";
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, message, Status.SUCCESS);
    }
}
