package com.app.NewBookRequest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.expr.NewArray;

@Service
@Transactional
public class NewBookRequestService {

	
	@Autowired
	private NewBookRequestRepository requestRepo;
	
	public void create(NewBookRequest request)
	{
		requestRepo.save(request);
	}
	
	public void update(NewBookRequest request)
	{
		requestRepo.save(request);
	}
	
	public void delet(Integer id)
	{
		requestRepo.deleteById(id);
	}
	
	public NewBookRequest find(Integer id)
	{
		return requestRepo.findById(id).get();
	}
	
	public List<NewBookRequest> getAll()
	{
		List<NewBookRequest> requests=new ArrayList<NewBookRequest>();
		requestRepo.findAll().forEach(requests::add);
		return requests;
	}
}

