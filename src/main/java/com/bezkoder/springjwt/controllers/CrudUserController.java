package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bezkoder.springjwt.models.User;
import java.util.List;

import com.bezkoder.springjwt.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class CrudUserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers(){
		return (List<User>) userRepository.findAll();
	}

	
}
