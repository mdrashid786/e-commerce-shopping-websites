package com.goboushop.gobousmartshopping.controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.repository.UsersRepository;

@RestController
@RequestMapping("/restusers")
public class userRestController {
	@Autowired
    private UsersRepository userRepository;
 
   
    @GetMapping("/uusers")
    public List<Users> getAllUsers() {
        return (List<Users>) userRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        java.util.Optional<Users> user = userRepository.findById(id);
 
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @PostMapping("/")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
    	  java.util.Optional<Users> userData = userRepository.findById(id);
 
        if (userData.isPresent()) {
            user = userData.get();
            user.setFullName(user.getFullName());
            user.setEmail(user.getEmail());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
