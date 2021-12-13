package com.medochemie.ordermanagement.OrderService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Order")
public class Order {
    @Id
    private String id;
    private Agent agent;
    private List<Product> products;

}
