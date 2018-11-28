package com.app.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService  {

	@Autowired
	BookRepository bookRepository;
	
	public void create(Book book) {
		bookRepository.save(book);
	}

	public void update(Book book) {
		bookRepository.save(book);
	}

	public void delet(Integer id) {
		bookRepository.deleteById(id);
	}

	public Book find(int id) {
		return bookRepository.findById(id).get();
	}

	public List<Book> getall() {
		List<Book> list = new ArrayList<>();
		bookRepository.findAll().forEach(list::add);
		return list;
	}

}
