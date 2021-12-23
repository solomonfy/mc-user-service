package com.medochemie.ordermanagement.OrderService.controller;

import com.medochemie.ordermanagement.OrderService.VO.Product;
import com.medochemie.ordermanagement.OrderService.VO.ResponseTemplateVO;
import com.medochemie.ordermanagement.OrderService.entity.Order;
import com.medochemie.ordermanagement.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final static Logger LOGGER = Logger.getLogger("");

    @Autowired
    private OrderRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders(){
        LOGGER.info("Returning all orders");
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id){
        LOGGER.info("Returning an order with an id " + id);
        try{
            return new ResponseEntity(repository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find_order_products/{id}")
    public ResponseEntity<List<Product>> getProductsOfOrder(@PathVariable String id){
        ResponseTemplateVO vo = new ResponseTemplateVO();

        log.info("Inside getProductsOfOrder method of OrderController, found an order of id " + id);
        Optional<Order> optionalEntity = repository.findById(id);
        Order order = optionalEntity.get();

        List<Product> productList = new ArrayList<>();
        List<String> productIds = order.getProductIds();

        int index = 0;
        Double total = 0D;
        for (String productId : productIds)
        {
            Product product = restTemplate.getForObject("http://MC-COMPANY-SERVICE/products/" + productId, Product.class);
            productList.add(product);
            total += product.getUnitPrice();
        }
        System.out.println(productList);

        try{
            return new ResponseEntity(productList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/page")
    public Map<String, Object> getAllOrdersInPage(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ){
        return repository.getAllOrdersInPage(pageNo, pageSize, sortBy);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        LOGGER.info("Adding a new order for " + order.getAgent().getAgentName());
        return new ResponseEntity(repository.insert(order), HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String id, @RequestBody Order order){
        Optional<Order> foundOrder = repository.findById(id);
        LOGGER.info("Updating an order with id " + order.getId());
        if (foundOrder.isPresent()){
            Order updatedOrder = foundOrder.get();
            updatedOrder.setAmount(order.getAmount());
            updatedOrder.setProductIds(order.getProductIds());
            updatedOrder.setShipment(order.getShipment());
            updatedOrder.setCreatedOn(order.getCreatedOn());
            return new ResponseEntity(repository.save(order), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{id}")
    public String deleteOrder(@PathVariable String id){
        repository.deleteById(id);
        return "Order number " + id + " has been deleted!";
    }


}
