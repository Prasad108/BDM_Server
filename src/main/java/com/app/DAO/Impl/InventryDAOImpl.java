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

import com.app.DAO.InventryDAO;
import com.app.pojo.Inventry;


@Repository("InventryDAO")
@Transactional
public class InventryDAOImpl implements InventryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Inventry inventry) {
		currentSession().save(inventry);
		}

	@Override
	public void update(Inventry inventry) {
		currentSession().update(inventry);

	}

	@Override
	public void delet(Inventry inventry) {
		currentSession().delete(inventry);

	}

	@Override
	public Inventry find(int id) {
		
		return currentSession().find(Inventry.class , id);
	}

	@Override
	public List<Inventry> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Inventry> query = builder.createQuery(Inventry.class);
        Root<Inventry> root = query.from(Inventry.class);
        query.select(root);
        Query<Inventry> q=currentSession().createQuery(query);
        List<Inventry> inventrys=q.getResultList();
		
		return inventrys;
	}

}
