package com.arizal.api.model;

import com.arizal.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User client = userDao.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        // Put Role
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(client.getAuthorities()));

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(), authList);

        return user;
    }

    public void insert(User user) throws Exception {
        userDao.save(user);
    }
}

