package com.arizal.api.dto.customer;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class RestCustomerUpdateRequest {

    @Valid
    @NotBlank
    private String id;
    private String name;
    private String birthDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
