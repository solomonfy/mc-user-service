package com.medochemie.ordermanagement.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    private String id;
    private String agentName;
    private String agentCode;
    private String countryId;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;
}
