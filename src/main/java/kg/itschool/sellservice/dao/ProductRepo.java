package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.dtos.ProductInfoDto;
import kg.itschool.sellservice.models.entities.Categories;
import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product getByName(String name);
    Product getById(Long id);

    List<Product> findAllByCategoriesAndActive(Categories categories, boolean active);
    List<Product> findAllByCategoriesAndActive(Categories categories, Boolean b);
    Product findByBarcode(String barcode);

}
