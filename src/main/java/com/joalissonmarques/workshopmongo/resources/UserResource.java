package com.joalissonmarques.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joalissonmarques.workshopmongo.domain.User;
import com.joalissonmarques.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping // ou podemos usar @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){	
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
