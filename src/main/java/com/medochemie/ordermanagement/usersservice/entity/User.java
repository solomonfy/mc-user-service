package com.medochemie.ordermanagement.usersservice.entity;

import com.medochemie.ordermanagement.usersservice.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String emailId;
    private String userName;
    private String password;
    private String profileImageUrl;
    private List<Role> roles;
    private List<Authority> authorities;
    private boolean isActive;
    private boolean isNotLocked;
    private String agentId;
    private String countryCode;
    private String createdBy;
    private Date joinDate;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;

//    public void setEmailId() {
//        this.emailId = this.firstName + "." + this.lastName + "@" + this.countryCode+ ".@medochemie.com";
//    }
}
