package com.app.DAO;

import java.util.List;

import com.app.pojo.Book;

public interface BookDAO {
	public void create(Book book);
	public void update(Book book);
	public void delet(Book book);
	public Book find(int id);
	public List<Book> getall();

}
