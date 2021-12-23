package com.medochemie.ordermanagement.company.controller;

import com.medochemie.ordermanagement.company.entity.Product;
import com.medochemie.ordermanagement.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = Logger.getLogger("");

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        log.info("Returning all products");
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        log.info("Returning a product with id" + id);
        Optional<Product> optionProduct = repository.findById(id);
        Product product = optionProduct.get();
        System.out.println(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }



    @GetMapping("/findAllById/{ids}")
    public ResponseEntity<List<Product>> getAllProductsById(@PathVariable List<String> ids){
        log.info("Returning all products with ids" + ids);

        List<Product> listOfProducts = new ArrayList<Product>();
        System.out.println(listOfProducts);

//        Sort sort = new Sort(Sort.Direction.ASC, "id"); // Or DESC

        List<Product> foundProducts = repository.findByIds(ids, Sort.by("id").descending());

//    Optional<List<Product>> foundProducts = Optional.ofNullable((repository.findAllProductsById(ids)));
//    List<Product> foundProducts = ((repository.findAllProductsById(ids)));
            System.out.println(foundProducts);

        if (foundProducts != null) {
            listOfProducts.addAll(foundProducts);
            System.out.println(listOfProducts);
            return new ResponseEntity(listOfProducts, null, HttpStatus.OK);

        } else {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        log.info("Adding new product with brand name" + product.getBrandName() );
        return new ResponseEntity(repository.save(product), HttpStatus.CREATED);
    }
}
