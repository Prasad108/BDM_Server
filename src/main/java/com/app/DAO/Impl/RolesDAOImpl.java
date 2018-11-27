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

import com.app.DAO.RolesDAO;
import com.app.pojo.Roles;
@Repository("RolesDAO")
@Transactional
public class RolesDAOImpl implements RolesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }
	
	@Override
	public void create(Roles role) {
		currentSession().save(role);

	}

	@Override
	public void update(Roles role) {
		currentSession().update(role);

	}

	@Override
	public Roles edit(int id) {
		return find(id);
	}

	@Override
	public void delet(Roles role) {
		currentSession().delete(role);

	}

	@Override
	public Roles find(int id) {
		return (Roles)currentSession().get(Roles.class, id);
	}

	@Override
	public List<Roles> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Roles> query = builder.createQuery(Roles.class);
        Root<Roles> root = query.from(Roles.class);
        query.select(root);
        Query<Roles> q=currentSession().createQuery(query);
        List<Roles> roles=q.getResultList();
		
		return roles;
	}

}
