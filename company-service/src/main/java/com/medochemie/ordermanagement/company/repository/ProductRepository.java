package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value="{ '_id' : ?0 }", fields="{ 'genericName' : 1, 'formulation' : 1, 'brandName': 1, 'strength':1, 'packSize':1 }")
    List<Product> findAllProductsById(List<String> ids);

    @Query("{_id: { $in: ?0 } })")
    List<Product> findByIds(List<String> ids, Sort sort);


    @Query(value="{ '_id' : ?0 }", fields="{ 'genericName' : 1, 'formulation' : 1, 'brandName': 1, 'strength':1, 'packSize':1, 'unitPrice': 1 }")
    Product findProductById(String id);

}
