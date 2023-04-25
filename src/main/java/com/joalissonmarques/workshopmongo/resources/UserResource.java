package com.joalissonmarques.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joalissonmarques.workshopmongo.domain.User;
import com.joalissonmarques.workshopmongo.dto.UserDTO;
import com.joalissonmarques.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping // ou podemos usar @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){	
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){	
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){	
		//aqui convertemos o DTO para User
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		//Codigo especifico do sprint para retornar url do novo recurso criado, assim ele pega o endereço do novo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//created retorna o 201, que é de criação
		return ResponseEntity.created(uri).build();
	}
	
}
