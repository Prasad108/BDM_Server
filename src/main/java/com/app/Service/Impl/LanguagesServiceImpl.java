package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.LanguagesDAO;
import com.app.Service.LanguagesService;
import com.app.pojo.Languages;

@Service("languagesService")
public class LanguagesServiceImpl implements LanguagesService {

	@Autowired
	LanguagesDAO languagesDAO;

	@Override
	public void create(Languages lang) {
		languagesDAO.create(lang);
		
	}

	@Override
	public void update(Languages lang) {
		languagesDAO.update(lang);
		
	}

	@Override
	public Languages edit(int id) {
		return languagesDAO.edit(id);
	}

	@Override
	public void delet(Languages lang) {
		languagesDAO.delet(lang);
		
	}

	@Override
	public Languages find(int id) {
		return languagesDAO.find(id);
	}

	@Override
	public List<Languages> getall() {
		return languagesDAO.getall();
	}

	
}
