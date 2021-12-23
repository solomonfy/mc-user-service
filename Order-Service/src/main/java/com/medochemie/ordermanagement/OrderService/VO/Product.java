package com.medochemie.ordermanagement.OrderService.VO;

import com.medochemie.ordermanagement.OrderService.enums.Formulation;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {

    private String productId;
    private String genericName;
    private Formulation formulation;
    private String brandName;
    private String strength;
    private String packSize;
    private Float unitPrice;
    private boolean active;
}
