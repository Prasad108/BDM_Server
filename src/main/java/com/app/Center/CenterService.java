package com.app.Center;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CenterService  {

	@Autowired
	CenterRepository centerRepository;
	
	public void create(Center center) {
		centerRepository.save(center);
	}

	public void update(Center center) {
		centerRepository.save(center);
	}

	public void delet(Integer id) {
		centerRepository.deleteById(id);
	}

	public Center find(int id) {
		return centerRepository.findById(id).get();
	}

	public List<Center> getall() {
		List<Center> list = new ArrayList<>();
		centerRepository.findAll().forEach(list::add);
		return list;
	}

}
