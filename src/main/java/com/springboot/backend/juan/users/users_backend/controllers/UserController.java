package com.springboot.backend.juan.users.users_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.juan.users.users_backend.entities.User;
import com.springboot.backend.juan.users.users_backend.services.UserService;

@CrossOrigin( originPatterns = "*" )
@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable Long id) {

        Optional<User> userOptional  = service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok( userOptional.get() );
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping 
    public ResponseEntity<?> create(@RequestBody User user, BindingResult result){

        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<String, String>();
            result.getFieldErrors().forEach(error ->{
                erros.put(error.getField(),"El campo "+ error.getField()+ error.getDefaultMessage() );
            });
            return ResponseEntity.badRequest().body(erros);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User>update( @RequestBody User user, BindingResult result ,@PathVariable Long id) {
        Optional<User> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            userDb.setEmail(user.getEmail());
            userDb.setLastname(user.getLastname());
            userDb.setName(user.getName());
            userDb.setPassword(user.getPassword());
            userDb.setUsername(user.getUsername());
            return ResponseEntity.ok( service.save(userDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> delete( @PathVariable Long id ){

        Optional<User> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
