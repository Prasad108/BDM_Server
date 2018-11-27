package com.app.Service;

import java.util.List;

import com.app.pojo.CbDetails;

public interface CbDetailsService {
	public void create(CbDetails cbDetails);
	public void update(CbDetails cbDetails);
	public void delet(CbDetails cbDetails);
	public CbDetails find(int id);
	public List<CbDetails> getall();
}
