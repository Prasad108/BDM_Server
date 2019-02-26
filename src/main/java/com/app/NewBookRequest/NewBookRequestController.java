package com.app.NewBookRequest;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.User.UserService;
@CrossOrigin
@RestController
@RequestMapping("/newBookRequest")
public class NewBookRequestController {
	
	@Autowired
	private NewBookRequestService service;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET, produces="application/json")
	public List<NewBookRequest> getAllRequests()
	{
		return service.getAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void saveRequest(@RequestBody NewBookRequest request, Principal principal)
	{
		request.setUser(userService.getCurrentUserDetails(principal.getName()));
		request.setStatus("pending");
		service.create(request);
	}
	
	@RequestMapping(value="/byUser",method=RequestMethod.GET, produces="application/json")
	public List<NewBookRequest> getAllRequestsByUser(Principal principal)
	{
		System.out.println(principal.getName());
		return service.getRequestListByUser(principal.getName());
	}
	
	@RequestMapping(value="/validate/{id}", method=RequestMethod.GET,produces="application/json")
	public NewBookRequest getRequestById(@PathVariable Integer id)
	{
		return service.findById(id);
	}
	
	@RolesAllowed("ROLE_SUPERADMIN")
	@RequestMapping(value="/confirmRequest/",method=RequestMethod.PUT)
	public void updateRequest(@RequestBody NewBookRequest request) {
		service.updateById(request.getId(), request.getStatus().toLowerCase(), request.getRemark());
	}
}