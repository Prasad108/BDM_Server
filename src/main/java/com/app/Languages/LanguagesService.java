package com.app.Languages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguagesService  {

	@Autowired
	LanguagesRepository languagesRepository;
	
	public Languages create(Languages languages) {
		return languagesRepository.save(languages);
	}

	public void update(Languages languages) {
		languagesRepository.save(languages);
	}

	public void delet(Integer id) {
		languagesRepository.deleteById(id);
	}

	public Languages find(int id) {
		return languagesRepository.findById(id).get();
	}

	public List<Languages> getall() {
		List<Languages> list = new ArrayList<>();
		languagesRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Languages> getAllLanguagesForBookNameInUsersInventory(Integer bookId,String userName) {
		
		return languagesRepository.getAllBookNameOfUsersInventory(bookId, userName).orElseThrow(() ->
		new RuntimeException("Error in findin users Bookname List -> username : " + userName));
	}

	public List<Languages> getLanguagesOfAllBooksHavingBookName(Integer id) {
		return languagesRepository.getLanguagesOfAllBooksHavingBookName(id)
				.orElseThrow(() -> new RuntimeException("Error While Fetching all Book Language"));
	}

}
