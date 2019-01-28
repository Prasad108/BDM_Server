package com.app.NewBookRequest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newBookRequest")
public class NewBookRequestController {
	
	@Autowired
	private NewBookRequestService service;
	
	@RequestMapping(value="/",method=RequestMethod.GET, produces="application/json")
	public List<NewBookRequest> getAllRequests()
	{
		return service.getAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void saveRequest(@RequestBody NewBookRequest request, Principal principal)
	{
		service.create(request);
	}
}
