package com.arizal.api.dto.customer;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class RestCustomerAddRequest {

    @Valid
    @NotBlank
    private String name;
    @Valid
    @NotBlank
    private String birthDate;

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
