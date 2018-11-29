package com.app.Type;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeService  {

	@Autowired
	TypeRepository typeRepository;
	
	public void create(Type type) {
		typeRepository.save(type);
	}

	public void update(Type type) {
		typeRepository.save(type);
	}

	public void delet(Integer id) {
		typeRepository.deleteById(id);
	}

	public Type find(int id) {
		return typeRepository.findById(id).get();
	}

	public List<Type> getall() {
		List<Type> list = new ArrayList<>();
		typeRepository.findAll().forEach(list::add);
		return list;
	}

}
