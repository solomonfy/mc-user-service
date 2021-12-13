package com.medochemie.ordermanagement.company.entity;

import com.medochemie.ordermanagement.company.enums.Formulation;

import lombok.Data;
import org.springframework.data.annotation.Id;
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
    private List<String> brandNames;
    private List<String> strengths;
    private List<String> packSizes;
    private Formulation formulation;
    private List<Site> productionSites;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;
}
