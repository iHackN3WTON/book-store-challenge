package com.bookstore.springboot.app.likes.models.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bookstore.springboot.app.likes.models.dao.LikeDao;
import com.bookstore.springboot.app.likes.models.entity.Book;
import com.bookstore.springboot.app.likes.models.entity.Like;
import com.bookstore.springboot.app.likes.models.entity.LikeVote;

@Service
public class LikeServiceImpl implements ILikeService {
	
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	@Transactional(readOnly = true)
	public List<Like> findAll() {
		return (List<Like>) likeDao.findAll();
	}

	@Override
	@Transactional
	public Like save(LikeVote likeVote) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("bookId", likeVote.getBookId().toString());
		Book book = clienteRest.getForObject("http://servicio-books/{bookId}", Book.class, pathVariables);
		if (book.getBookId() == 0) {
			Like likeFail = new Like();
			likeFail.setBookId(0L);
			likeFail.setLikes(0);
			List<String> customers = Arrays.asList("Libro no encontrado");
			likeFail.setCustomers(customers);
			return likeFail;
		}else {
			Like like = likeDao.findById(book.getBookId()).orElse(new Like(book.getBookId(),0,new ArrayList<String>()));
			like.setLikes(like.getLikes()+1);
			ArrayList<String> customers = new ArrayList<String>();
			for (int j=0; j < like.getCustomers().size(); j++) customers.add(like.getCustomers().get(j));
			Boolean customerYaRegistrado = false;
			for (int i=0; i < customers.size(); i++) if (customers.get(i).equals(likeVote.getCustomerEmail())) customerYaRegistrado = true;
			if (!customerYaRegistrado) customers.add(likeVote.getCustomerEmail());
			like.setCustomers((List<String>) customers);
			return likeDao.save(like);
		}
	}

	@Override
	public void deleteByBookId(Long bookId) {
		List<Like> likes = ((List<Like>) likeDao.findAll()).stream().filter(l -> l.getBookId() == bookId).collect(Collectors.toList());
		likeDao.deleteAll(likes);	
	}

}
