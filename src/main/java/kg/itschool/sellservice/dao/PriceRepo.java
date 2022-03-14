package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.entities.Prices;
import kg.itschool.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Prices,Long> {
    List<Prices> findAllByProducts(Product product);

    @Query(value = "select price from prices where products_id=?1 and active=true and start_date<=(select now()) and end_date>=(select now())",nativeQuery = true)
    double findPriceByProduct(Long price_id);
}
