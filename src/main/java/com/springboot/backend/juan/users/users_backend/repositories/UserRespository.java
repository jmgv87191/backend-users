package com.springboot.backend.juan.users.users_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.juan.users.users_backend.entities.User;

public interface UserRespository extends CrudRepository<User, Long> {

}
