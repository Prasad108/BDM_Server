package com.app.Challan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChallanService  {

	@Autowired
	ChallanRepository challanRepository;
	
	public void create(Challan challan) {
		challanRepository.save(challan);
	}

	public void update(Challan challan) {
		challanRepository.save(challan);
	}

	public void delet(Integer id) {
		challanRepository.deleteById(id);
	}

	public Challan find(int id) {
		return challanRepository.findById(id).get();
	}

	public List<Challan> getall() {
		List<Challan> list = new ArrayList<>();
		challanRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Challan> findByUserByIssuedTo(Integer id) {
		// TODO Auto-generated method stub
		return challanRepository.findByUserByIssuedTo_Id(id);
	}

}
