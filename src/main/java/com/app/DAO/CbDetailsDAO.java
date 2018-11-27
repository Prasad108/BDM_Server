package com.app.DAO;

import java.util.List;

import com.app.pojo.CbDetails;

public interface CbDetailsDAO {
	public void create(CbDetails cbDetails);
	public void update(CbDetails cbDetails);
	public void delet(CbDetails cbDetails);
	public CbDetails find(int id);
	public List<CbDetails> getall();

}
