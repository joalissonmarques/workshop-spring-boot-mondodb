package com.joalissonmarques.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joalissonmarques.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	//Colocamos o IgnoreCase para ele aceitar maiuscula e minuscula
	List<Post> findByTitleContainingIgnoreCase(String text);
}
