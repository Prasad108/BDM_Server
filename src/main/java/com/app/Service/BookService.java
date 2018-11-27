package com.app.Service;

import java.util.List;

import com.app.pojo.Book;

public interface BookService {
	public void create(Book book);
	public void update(Book book);
	public void delet(Book book);
	public Book find(int id);
	public List<Book> getall();
}
