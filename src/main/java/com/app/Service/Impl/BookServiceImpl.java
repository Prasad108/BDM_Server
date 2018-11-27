package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.BookDAO;
import com.app.Service.BookService;
import com.app.pojo.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;
	
	@Override
	public void create(Book book) {
		bookDAO.create(book);
	}

	@Override
	public void update(Book book) {
		bookDAO.update(book);
	}

	@Override
	public void delet(Book book) {
		bookDAO.delet(book);
	}

	@Override
	public Book find(int id) {
		return bookDAO.find(id);
	}

	@Override
	public List<Book> getall() {
		return bookDAO.getall();
	}

}
