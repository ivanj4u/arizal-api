package com.arizal.api.dto.customer;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class RestCustomerDeleteRequest {

    @Valid
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
