package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
//    @Query(value="{ 'firstname' : ?0 }", fields="{ 'firstname' : 1, 'lastname' : 1}")
    List<Product> findAllProductsById(List<String> Ids);

    @Query("{_id: { $in: ?0 } })")
    List<Product> findByIds(List<String> ids, Sort sort);
}
