package com.arizal.api.model;

import com.arizal.api.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends CrudRepository<Customer, Long> {

    @Query("select cus from Customer cus where cus.name like :name")
    List<Customer> findByUsername(@Param("name") String name);

    @Query("select cus from Customer cus where cus.name like :name and cus.birthDate = :birthDate")
    List<Customer> findByUsernameAndBirthDate(@Param("name") String name, @Param("birthDate") String birthDate);

    @Override
    List<Customer> findAll();
}
