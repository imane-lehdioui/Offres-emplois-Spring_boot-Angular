package com.bezkoder.springjwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Cv;
import com.bezkoder.springjwt.models.Offre;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.CvRepository;
import com.bezkoder.springjwt.repository.OffreRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	
	 @Autowired
	  UserRepository userRepository;
	 
	 @Autowired
	 OffreRepository offreRepository;
	 
	 @Autowired
	 CvRepository cvRepository;
	 
	 
	 
	 
	 
	//Afficher la liste des offres
	 @GetMapping("/listoffre")
		public List<Offre> getAllOffre(){
			return offreRepository.findAll();
			
		}
		
	
	  // get all users
		@GetMapping("/listuser")
		public List<User> getAllEmployees(){
			return userRepository.findAll();
		}
		
		// creer user
		@PostMapping("/creeruser")
		public User createUser(@RequestBody User user) {
			return userRepository.save(user);
		}
		
		// creer offre
				@PostMapping("/creeroffre")
				public Offre createOffre(@RequestBody Offre offre) {
					return offreRepository.save(offre);
				}
				
				
				
				// delete offre
				@DeleteMapping("/listoffre/{id}")
				public ResponseEntity<HttpStatus> deleteOffre(@PathVariable("id") long id) {
					try {
						userRepository.deleteById(id);
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					} catch (Exception e) {
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
				
				// creer cv
				@PostMapping("/creercv")
				public Cv createCv(@RequestBody Cv cv) {
					return cvRepository.save(cv);
				}
				
				//Afficher la liste des cv
				 @GetMapping("/liscv")
					public List<Cv> getAllcv(){
						return cvRepository.findAll();
						
					}
		

				
				
				
				
				
				
				
				
				
				
				
		
		// delete user rest api
		@DeleteMapping("/listuser/{id}")
		public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
			try {
				userRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Modifier user
		@PutMapping("/listuser/{id}")
		public ResponseEntity<User> updateTutorial(@PathVariable("id") long id, @RequestBody User user) {
			Optional<User> userData = userRepository.findById(id);

			if (userData.isPresent()) {
				User _user = userData.get();
				_user.setUsername(user.getUsername());
				_user.setEmail(user.getEmail());
				_user.setPassword(user.getPassword());
				_user.setRoles(user.getRoles());
				
				return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// chercher user byId
		@GetMapping("/listuser/{id}")
		public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
			Optional<User> userData = userRepository.findById(id);

			if (userData.isPresent()) {
				return new ResponseEntity<>(userData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		
		
		
		
	
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }
  

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
