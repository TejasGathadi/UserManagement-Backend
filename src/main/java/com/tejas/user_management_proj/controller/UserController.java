package com.tejas.user_management_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.user_management_proj.exception.UserNotFoundException;
import com.tejas.user_management_proj.model.User;
import com.tejas.user_management_proj.repository.UserRepository;
 
@RestController
@CrossOrigin("https://user-info-k4703mgbg-tejas-s-projects.vercel.app/")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	
	/* used to send data to the db from the user by post mapping */
	
	@PostMapping("/addUser")
	User newUser(@RequestBody User newUser ) {
		return userRepo.save(newUser);
	}
	
	
	
	
	/* to retrieve the data from db all the data use get mapping annotation*/
	
	@GetMapping("/getUsers")
	List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	
	/* to get the user by user id so when user gives user id it gives that specific id data 
	 * and if not there it throws error */
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	/* Edit the user by user id so that user can edit the user accordingly
	 * */
	
	@PutMapping("/user/{id}")
	User  updateUser(@RequestBody User newUser, @PathVariable Long id){
		return userRepo.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return userRepo.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	  
	
	/* delete the specific user and also use the id to delete the user*/
	
	@DeleteMapping("user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		
		userRepo.deleteById(id);
		return "User With Id " + id + " deleted Successfully";
	}
	
	

}
