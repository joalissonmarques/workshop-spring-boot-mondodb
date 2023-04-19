package com.joalissonmarques.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joalissonmarques.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping // ou podemos usar @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User jojo = new User("1", "joalisson", "joalison@gmail.com");
		User mara = new User("2", "Mayara", "mayara@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(jojo, mara));
		return ResponseEntity.ok().body(list);
	}

}
