package com.app.DAO.Impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.DAO.ChallanDAO;
import com.app.pojo.Challan;

@Transactional
@Repository("ChallanDAO")
public class ChallanDAOImpl implements ChallanDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Challan challan) {
		currentSession().save(challan);

	}

	@Override
	public void update(Challan challan) {
		currentSession().update(challan);

	}

	@Override
	public void delet(Challan challan) {
		currentSession().delete(challan);

	}

	@Override
	public Challan find(int id) {
		return currentSession().find(Challan.class, id);
	}

	@Override
	public List<Challan> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Challan> query = builder.createQuery(Challan.class);
        Root<Challan> root = query.from(Challan.class);
        query.select(root);
        Query<Challan> q=currentSession().createQuery(query);
        List<Challan> challansList=q.getResultList();
		
		return challansList;
	}

}
