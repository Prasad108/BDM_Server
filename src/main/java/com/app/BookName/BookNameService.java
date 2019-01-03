package com.app.BookName;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookNameService  {

	@Autowired
	BookNameRepository bookNameRepository;
	
	public BookName create(BookName bookName) {
		return bookNameRepository.save(bookName);
	}

	public BookName update(BookName bookName) {
		return bookNameRepository.save(bookName);
	}

	public void delet(Integer id) {
		bookNameRepository.deleteById(id);
	}

	public BookName find(int id) {
		return bookNameRepository.findById(id).get();
	}

	public List<BookName> getall() {
		List<BookName> list = new ArrayList<>();
		bookNameRepository.findAll().forEach(list::add);
		return list;
	}

}
