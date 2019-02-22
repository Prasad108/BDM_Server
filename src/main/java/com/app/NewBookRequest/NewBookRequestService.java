package com.app.NewBookRequest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.User.UserRepository;
import com.app.User.UserService;

import javassist.expr.NewArray;

@Service
@Transactional
public class NewBookRequestService {

	
	@Autowired
	private NewBookRequestRepository requestRepo;
	
	
	@Autowired
	UserService userService;
	
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
	
	public NewBookRequest findById(Integer id)
	{
		return requestRepo.findById(id).get();
	}
	
	public List<NewBookRequest> getAll()
	{
		List<NewBookRequest> requests=new ArrayList<NewBookRequest>();
		requestRepo.findAll().forEach(requests::add);
		return requests;
	}

	public List<NewBookRequest> getRequestListByUser(String name) {
		
		List<NewBookRequest> requests=new ArrayList<NewBookRequest>();
		requests=requestRepo.getNewBookRequestsByUser(userService.getCurrentUserDetails(name));
		return requests;
	}
	
	public void updateById(int id,String status,String remark)
	{
		requestRepo.updateRequestById(id, status, remark);
	}
}

