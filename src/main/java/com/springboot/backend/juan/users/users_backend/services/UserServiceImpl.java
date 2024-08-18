package com.springboot.backend.juan.users.users_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.juan.users.users_backend.entities.User;
import com.springboot.backend.juan.users.users_backend.repositories.UserRespository;


@Service
public class UserServiceImpl implements UserService {

    private UserRespository respository;
    
    public UserServiceImpl(UserRespository respository) {
        this.respository = respository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List)this.respository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return this.respository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)

    public Optional<User> findById(Long id) {

        return this.respository.findById(id);

    }

    @Override
    public User save(User user) {
        return this.respository.save(user);
    }

    @Override
    public void deleteById(Long id) {

        this.respository.deleteById(id);

    }



}
