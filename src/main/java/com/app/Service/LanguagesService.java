package com.app.Service;

import java.util.List;

import com.app.pojo.Languages;

public interface LanguagesService {


	public void create(Languages lang);
	public void update(Languages lang);
	public Languages edit(int id);
	public void delet(Languages lang);
	public Languages find(int id);
	public List<Languages> getall();



}
