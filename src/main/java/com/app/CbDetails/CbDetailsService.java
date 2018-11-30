package com.app.CbDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CbDetailsService  {

	@Autowired
	CbDetailsRepository cbDetailsRepository;
	
	public void create(CbDetails cbDetails) {
		cbDetailsRepository.save(cbDetails);
	}

	public void update(CbDetails cbDetails) {
		cbDetailsRepository.save(cbDetails);
	}

	public void delet(Integer id) {
		cbDetailsRepository.deleteById(id);
	}

	public CbDetails find(int id) {
		return cbDetailsRepository.findById(id).get();
	}

	public List<CbDetails> getall() {
		List<CbDetails> list = new ArrayList<>();
		cbDetailsRepository.findAll().forEach(list::add);
		return list;
	}

}
