package com.app.Challan;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.CbDetails.CbDetailsService;
import com.app.User.User;
import com.app.User.UserRepository;
import com.app.User.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class ChallanService  {

	@Autowired
	ChallanRepository challanRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CbDetailsService cbDetailsService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	final JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
	
	public Challan create(Challan challan) {
		return challanRepository.save(challan);
	}

	public Challan update(Challan challan) {
		return challanRepository.save(challan);
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
	
	public String getDetailedChallan(int id) throws IOException {
		Challan ch=find(id);
		
		return getChallanDetailsWithFullUserAndCbDetails(ch).toString();
	}
	
	private JsonNode getChallanDetailsWithFullUserAndCbDetails(Challan ch) throws IOException {
		JsonNode rootNode = mapper.valueToTree(ch);
		((ObjectNode) rootNode).set("userByIssuedTo",mapper.valueToTree(userService.find(ch.getUserByIssuedTo().getId())));
		((ObjectNode) rootNode).set("userByIssuedBy",mapper.valueToTree(userService.find(ch.getUserByIssuedBy().getId())));
		JsonNode readCbDetailsesNode = rootNode.path("cbDetailses");
		ArrayNode cbDetailNodeArray=jsonFactory.arrayNode();
			for(JsonNode cbDetail : readCbDetailsesNode) {
			JsonNode  cb=	mapper.readTree(cbDetailsService.detaliedCbDetail(cbDetail.path("id").asInt()));
			cbDetailNodeArray.add(cb);
		}
		((ObjectNode) rootNode).replace("cbDetailses", cbDetailNodeArray);
		return rootNode;
	}
	
	private JsonNode getChallanDetailsWithFullUser(Challan ch) throws IOException {
		JsonNode rootNode = mapper.valueToTree(ch);
		((ObjectNode) rootNode).set("userByIssuedTo",mapper.valueToTree(userService.find(ch.getUserByIssuedTo().getId())));
		((ObjectNode) rootNode).set("userByIssuedBy",mapper.valueToTree(userService.find(ch.getUserByIssuedBy().getId())));
		return rootNode;
	}
	
	public List<Challan> getChallanOfInstitute(){
		
		return null;
	}

	public ArrayNode getAllOfUsersCenter(String name) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode challanArray = mapper.createArrayNode();
		
		for(Challan ch : challanRepository.getAllChallanOfUsersCenter(name)) {
			challanArray.add(getChallanDetailsWithFullUser(ch));
		}
	
		return challanArray;
	}
	
	public Challan[] justTry(String name) {
		return challanRepository.justTry(name);
	}
	
	public ArrayNode getUserSpecificChallanList(String name) throws IOException {
		  ObjectMapper mapper = new ObjectMapper(); 
		  ArrayNode challanArray = mapper.createArrayNode();
		  System.out.println("name----"+name);
		  for(Challan ch : challanRepository.getListOfUserSpecificChallan(name))
		  {	  
			  System.out.println("ch----"+ch.getId());
			  challanArray.add(getChallanDetailsWithFullUser(ch));
		  }
		  System.out.println("---------"+challanArray.asText());
		  return challanArray;
	}
	
	public Challan creatNewChallan(Integer id,Principal principal) {
		Challan challan= new Challan();
		User issuedBy = userRepository.findByUsername(principal.getName()).orElseThrow(() -> 
						new UsernameNotFoundException("User Not Found with -> username : " + principal.getName()));
		challan.setUserByIssuedBy(issuedBy);
		User issuedTo = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Requested issued_To user not Present!"));
		challan.setUserByIssuedTo(issuedTo);
		challan.setIssuedDate(new Date());
		challan.setSettled((byte)0);
		challan.setTotalAmount(0);
		// challan.setReceivedAmount(0);
		return challanRepository.save(challan);
	}
	
	public boolean checkIfChallanIsSettled(Integer id) {
		return challanRepository.checkIfChallanIsSettled(id).isPresent();
	}

}
