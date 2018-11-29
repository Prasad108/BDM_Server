package com.app.Inventry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
