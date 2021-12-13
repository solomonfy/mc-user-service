package com.medochemie.ordermanagement.OrderService.entity;

import lombok.Data;

import java.util.List;

@Data
public class Agent {

    private String agentId;
    private String agentName;
    private List<String> orders;
}
