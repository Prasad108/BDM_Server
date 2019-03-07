package com.app.Inventry;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.BookName.BookName;
import com.app.Languages.Languages;
import com.app.Type.Type;

@Service
@Transactional
public class InventryService  {

	@Autowired
	InventryRepository inventryRepository;

	public Inventry create(Inventry inventry) {
		return inventryRepository.save(inventry);
	}

	public Inventry update(Inventry inventry) {
		return inventryRepository.save(inventry);
	}

	public void delet(Integer id) {
		inventryRepository.deleteById(id);
	}

	public Inventry find(int id) {
		return inventryRepository.findById(id).get();
	}

	public List<Inventry> getall() {
		List<Inventry> list = new ArrayList<>();
		inventryRepository.findAll().forEach(list::add);
		return list;
	}
	

	public Optional<List<Object[]>> findByUserCenterLangNameandType(BookName bn, Languages l, Type t, String username) {
		return inventryRepository.findByUserCenterLangNameandType(bn,l,t,username);
	};

	public String getInventryDetailsOfCurrentUser(String username) {
		return inventryRepository.getInventoryJSON(username);
	}

}
