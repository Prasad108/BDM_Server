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

import com.app.DAO.CbDetailsDAO;
import com.app.pojo.CbDetails;


@Repository("CbDetailsDAO")
@Transactional
public class CbDetailsDAOImpl implements CbDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }
	 
	@Override
	public void create(CbDetails cbDetails) {
		currentSession().save(cbDetails);

	}

	@Override
	public void update(CbDetails cbDetails) {
		currentSession().update(cbDetails);

	}

	@Override
	public void delet(CbDetails cbDetails) {
		currentSession().delete(cbDetails);

	}

	@Override
	public CbDetails find(int id) {
		return currentSession().find(CbDetails.class, id);
	}

	@Override
	public List<CbDetails> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<CbDetails> query = builder.createQuery(CbDetails.class);
        Root<CbDetails> root = query.from(CbDetails.class);
        query.select(root);
        Query<CbDetails> q=currentSession().createQuery(query);
        List<CbDetails> cbDetails=q.getResultList();
		
		return cbDetails;
	}

}
