package com.app.Challan;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChallanRepository extends CrudRepository<Challan, Integer> {
	
	public List<Challan> findByUserByIssuedTo_Id(Integer id);

}
