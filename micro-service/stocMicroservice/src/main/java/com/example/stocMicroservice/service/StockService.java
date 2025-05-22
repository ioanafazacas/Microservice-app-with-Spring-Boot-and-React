package com.example.stocMicroservice.service;

import ch.qos.logback.core.joran.util.StringToObjectConverter;
import com.example.stocMicroservice.client.FlowerClient;
import com.example.stocMicroservice.client.ShopClient;
import com.example.stocMicroservice.domain.Stock;
import com.example.stocMicroservice.domain.dto.*;
import com.example.stocMicroservice.domain.mapper.StockMapper;
import com.example.stocMicroservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    private final StockMapper mapper;
    @Autowired
    private FlowerClient flowerClient;
    @Autowired
    private ShopClient shopClient;

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

    public List<FlowerColorsDTO> findAllFlowersWithColor() {
        //var stocks = stockRepository.findAll();
        var flowers = flowerClient.getAllFlowers();
        List<FlowerColorsDTO> list = new ArrayList<>();
        for(FlowerDTO flower: flowers){
            List<Stock> stocks = stockRepository.findByFlowerId(flower.getId());
            List<String> colors = stocks.stream().map(Stock::getColor).distinct()
                    .collect(Collectors.toList());
            FlowerColorsDTO flowerColorsDTO= FlowerColorsDTO.builder()
                    .id(flower.getId())
                    .name(flower.getName())
                    .purchase_price(flower.getPurchase_price())
                    .sale_price(flower.getSale_price())
                    .image(flower.getImage())
                    .colors(colors)
                    .build();
            list.add(flowerColorsDTO);
        }
        return list;
    }

    public List<ShopColorsDTO> findAllShopsForFlower(String name) {
        var flower = flowerClient.getFlowerByName(name);
        List<Stock> stocks = stockRepository.findByFlowerId(flower.getId());
        List<ShopColorsDTO> shops = new ArrayList<>();
        Map<ShopDTO,List<String>> shopWithColors = new HashMap<>();
        for(Stock stock: stocks){
            var shop = shopClient.getShopById(stock.getShop_id());
            if(shopWithColors.containsKey(shop)){
                List<String> colors = shopWithColors.get(shop);
                colors.add(stock.getColor());
                shopWithColors.put(shop,colors);
            }else{
                List<String> colors = new ArrayList<>();
                colors.add(stock.getColor());
                shopWithColors.put(shop,colors);
            }

        }
        for(ShopDTO shopDTO: shopWithColors.keySet()){
            ShopColorsDTO shopColorsDTO = ShopColorsDTO.builder()
                    .name(shopDTO.getName())
                    .address(shopDTO.getAddress())
                    .colors(shopWithColors.get(shopDTO)).build();

            shops.add(shopColorsDTO);
        }
        return shops;
    }
}
