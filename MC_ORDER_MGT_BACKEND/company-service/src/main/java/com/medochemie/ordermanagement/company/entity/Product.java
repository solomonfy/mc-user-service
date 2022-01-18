package com.medochemie.ordermanagement.company.entity;

import com.medochemie.ordermanagement.company.VO.Order;
import com.medochemie.ordermanagement.company.enums.Formulation;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Product")
public class Product {
    @Id
    private String id;
    private String chemicalName;
    private String genericName;
    private Formulation formulation;
    private String brandName;
    private String strength;
    private String packSize;
    private Float unitPrice;
    private List<Site> productionSites;
    private boolean active;
    private String imageUrl;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
}
