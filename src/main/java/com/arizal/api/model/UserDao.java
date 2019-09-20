package com.arizal.api.model;

import com.arizal.api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User, Long> {

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

}
