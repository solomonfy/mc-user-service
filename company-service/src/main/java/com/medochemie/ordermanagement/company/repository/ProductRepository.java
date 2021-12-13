package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
