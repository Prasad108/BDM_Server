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

import com.app.DAO.TypeDAO;
import com.app.pojo.Type;

@Repository("TypeDAO")
@Transactional
public class TypeDAOImpl implements TypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Type type) {
		currentSession().save(type);
	}

	@Override
	public void update(Type type) {
		currentSession().update(type);
	}

	@Override
	public Type edit(int id) {
		return find(id);
	}

	@Override
	public void delet(Type type) {
		currentSession().delete(type);
	}

	@Override
	public Type find(int id) {
		return currentSession().find(Type.class, id);
	}

	@Override
	public List<Type> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Type> query = builder.createQuery(Type.class);
        Root<Type> root = query.from(Type.class);
        query.select(root);
        Query<Type> q=currentSession().createQuery(query);
        List<Type> type=q.getResultList();
		
		return type;
	}

}
