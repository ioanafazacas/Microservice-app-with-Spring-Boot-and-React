package com.example.stocMicroservice.service;

import ch.qos.logback.core.joran.util.StringToObjectConverter;
import com.example.stocMicroservice.domain.Stock;
import com.example.stocMicroservice.domain.dto.StockDTO;
import com.example.stocMicroservice.domain.mapper.StockMapper;
import com.example.stocMicroservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    private final StockMapper mapper;

    public void save(StockDTO stockDTO){
        stockRepository.save(mapper.stockDtoToEntity(stockDTO));
    }
    public void deleteByFlowerId(int flowerId){
        stockRepository.deleteByFlowerId(flowerId);
    }

    public void deleteByShopId(int shopId){
        stockRepository.deleteByShopId(shopId);
    }

    //s-ar putea sa mai am nevoie de un delete in functie de flower, shop and color;
    //daca vreau sa sterg culoarea unei anumite flori
    public List<StockDTO> findAll(){
        return mapper.stockListEntityToDto(stockRepository.findAll());
    }
    public List<StockDTO> findAllByFlowerId(int flowerId){
        return mapper.stockListEntityToDto(stockRepository.findByFlowerId(flowerId));
    }
    public List<StockDTO> findAllByShopId(int shopId){
        return mapper.stockListEntityToDto(stockRepository.findByShopId(shopId));
    }

    public List<StockDTO> findOutOfStockFlowers(){
        return mapper.stockListEntityToDto(stockRepository.findOutOfStockFlowers());
    }
    public List<StockDTO> findOutOfStockFlowersByShop(int shopId){
        return mapper.stockListEntityToDto(stockRepository.findOutOfStockFlowersByShop(shopId));
    }

    public void saleFlower(int stockId){
        Optional<Stock> existing = stockRepository.findById(stockId);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not update. Stock not found with id: " + stockId);
        }

        Stock stock = existing.get();
        stockRepository.updateQuantity(stockId, stock.getQuantity()-1);
    }

    public void updateQuantity(int stockId, int newQuantity){
        Optional<Stock> existing = stockRepository.findById(stockId);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not update. Stock not found with id: " + stockId);
        }

        stockRepository.updateQuantity(stockId,newQuantity);
    }

    public List<StockDTO> getAvailableStockByShopAndFlower(int shopId, int flowerId) {
        return mapper.stockListEntityToDto(stockRepository.findAvailableFlowersByShopAndFlowerId(shopId, flowerId));
    }

}
