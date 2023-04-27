package com.joalissonmarques.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joalissonmarques.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	//Colocamos o IgnoreCase para ele aceitar maiuscula e minuscula
	List<Post> findByTitleContainingIgnoreCase(String text);

	//Consulta manual, expressão regular, o ?0 é o primeiro parametro e o i é o case
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//Buscando por intervalo de datas e pesquisa de dados de texto
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
