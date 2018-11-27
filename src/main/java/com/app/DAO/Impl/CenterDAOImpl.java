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

import com.app.DAO.CenterDAO;
import com.app.pojo.Center;

@Repository("CenterDAO")
@Transactional
public class CenterDAOImpl implements CenterDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Center center) {
		currentSession().save(center);

	}

	@Override
	public void update(Center center) {
		currentSession().update(center);
	}

	@Override
	public Center edit(int id) {
		return find(id);
	}

	@Override
	public void delet(Center center) {
		currentSession().delete(center);
	}

	@Override
	public Center find(int id) {
		return currentSession().find(Center.class, id);
	}

	@Override
	public List<Center> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Center> query = builder.createQuery(Center.class);
        Root<Center> root = query.from(Center.class);
        query.select(root);
        Query<Center> q=currentSession().createQuery(query);
        List<Center> centers=q.getResultList();
		
		return centers;
	}

}
