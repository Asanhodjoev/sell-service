package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.PriceRepo;
import kg.itschool.sellservice.exceptions.SaleException;
import kg.itschool.sellservice.mappers.PriceMapper;
import kg.itschool.sellservice.models.dtos.PriceDto;
import kg.itschool.sellservice.models.dtos.PriceRequestDto;
import kg.itschool.sellservice.models.entities.Prices;
import kg.itschool.sellservice.models.entities.Product;
import kg.itschool.sellservice.services.PriceService;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private ProductService productService;

    public PriceServiceImpl(PriceRepo priceRepo, ProductService productService) {
        this.priceRepo = priceRepo;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> createPrice(PriceRequestDto priceRequestDto) {
//        System.out.println(priceRequestDto.toString());
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(priceRequestDto.getPrice());
        priceDto.setStart_date(LocalDateTime.parse(priceRequestDto.getStart_date()));
        priceDto.setEnd_date(LocalDateTime.parse(priceRequestDto.getEnd_date()));
        priceDto.setProducts_id(priceRequestDto.getProducts_id());
//        System.out.println(priceDto.toString());

        Product product = productService.getById(priceDto.getProducts_id());
        System.out.println(product.toString());
        if(Objects.isNull(product)) {
            throw new SaleException("Ошибка", "Такого продукта нету");
        }
        List<Prices> pricesList = priceRepo.findAllByProducts(product);
        if(Objects.nonNull(pricesList) && !pricesList.isEmpty()) {
            pricesList.stream().filter(x -> x.getStart_date().isBefore(priceDto.getStart_date()) && x.getEnd_date().isAfter(priceDto.getEnd_date())
                                         || x.getStart_date().isAfter(priceDto.getStart_date()) && x.getEnd_date().isBefore(priceDto.getEnd_date())
                                          || x.getStart_date().isAfter(priceDto.getStart_date()) && x.getStart_date().isBefore(priceDto.getEnd_date())
                                         || x.getEnd_date().isAfter(priceDto.getStart_date()) && x.getEnd_date().isBefore(priceDto.getEnd_date())
                                         || x.getStart_date().isEqual(priceDto.getStart_date()) || x.getEnd_date().isEqual(priceDto.getEnd_date()))
                    .forEach(x -> {x.setActive(false);
                    priceRepo.save(x);});
        }
        Prices prices = new Prices();
        prices.setPrice(priceDto.getPrice());
        prices.setStart_date(priceDto.getStart_date());
        prices.setEnd_date(priceDto.getEnd_date());
        prices.setProducts(product);
        prices.setActive(true);
        prices = priceRepo.save(prices);
//        System.out.println(price.toString());
        return ResponseEntity.ok(PriceMapper.INSTANCE.priceToPriceResponseDto(prices));
    }

    @Override
    public double findActualPrice(Long product_id) {
        try {
            double z = priceRepo.findPriceByProduct(product_id);
            return z;
        } catch (Exception e){
            return 0.0;
        }
    }
}
