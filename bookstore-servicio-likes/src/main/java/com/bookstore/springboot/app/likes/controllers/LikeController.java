package com.bookstore.springboot.app.likes.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.springboot.app.likes.models.entity.Like;
import com.bookstore.springboot.app.likes.models.entity.LikeVote;
import com.bookstore.springboot.app.likes.models.service.ILikeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.SneakyThrows;

@RestController
public class LikeController {
	
	private final Logger log = LoggerFactory.getLogger(LikeController.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	private ILikeService likeService;
	
	@GetMapping("/")
	public List<Like> listar() {
		log.info("Solicitando listado de likes");
		return likeService.findAll();
	}
	
	@SneakyThrows
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="votes")
	public Like crear(@RequestBody LikeVote likeVote) {
		Like resultado = cbFactory.create("votes").run(() -> likeService.save(likeVote), e -> crearFail(likeVote, e));
		log.info("Guardando info de likes del libro " + resultado.getBookId());
		return resultado;
	}
	
	public Like crearFail(LikeVote likeVote, Throwable e) {
		log.error(e.getMessage());
		return new Like(0L, 0, List.of("Error de conexion"));		
	}
	
	@SneakyThrows
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByBookId(@PathVariable Long bookId) {
		log.info("Eliminando info de likes del libro " + bookId);
		likeService.deleteByBookId(bookId);
	}
}
