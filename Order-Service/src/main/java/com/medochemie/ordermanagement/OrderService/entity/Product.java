package com.medochemie.ordermanagement.OrderService.entity;

import lombok.Data;

@Data
public class Product {

    private String genericName;
    private String brandName;
    private String strength;
    private String packSize;
}
