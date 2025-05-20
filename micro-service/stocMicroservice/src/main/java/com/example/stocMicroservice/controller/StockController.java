package com.example.stocMicroservice.controller;

import com.example.stocMicroservice.domain.dto.StockDTO;
import com.example.stocMicroservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("save")
    public void save(@RequestBody StockDTO stockDTO){
        stockService.save(stockDTO);
    }

    @DeleteMapping("delete/flower/{flowerId}")
    public void deleteByFlowerId(@PathVariable int flowerId){
        stockService.deleteByFlowerId(flowerId);
    }

    @GetMapping("/all")
    public List<StockDTO> getAllStock() {
        return stockService.findAll();
    }

    @GetMapping("shop/{shopId}")
    public List<StockDTO> getAllFlowersByShop(@PathVariable int shopId){
        return stockService.findAllByShopId(shopId);
    }

    @GetMapping("flower-colors/{flowerId}")
    public List<StockDTO> getAllColorsByFlower(@PathVariable int flowerId){
        return stockService.findAllByFlowerId(flowerId);
    }

    @GetMapping("/shop/{shopId}/flower/{flowerId}")
    public ResponseEntity<List<StockDTO>> getAvailableStockByShopAndFlower(
            @PathVariable int shopId,
            @PathVariable int flowerId) {

        List<StockDTO> stockList = stockService.getAvailableStockByShopAndFlower(shopId, flowerId);

        if (stockList.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(stockList); // 200 OK
    }

    @PutMapping("sale/{id}")
    public void sale(@PathVariable int id){
        stockService.saleFlower(id);
    }

    @PutMapping("update/{id}")
    public void updateStock(@PathVariable int id, @RequestBody StockDTO stockDTO){
        stockService.updateQuantity(id,stockDTO.getQuantity());
    }
}
