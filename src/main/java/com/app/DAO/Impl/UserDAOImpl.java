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

import com.app.DAO.UserDAO;
import com.app.pojo.User;

@Repository("UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(User user) {
		currentSession().save(user);
		
	}

	@Override
	public void update(User user) {
		currentSession().update(user);
		
	}

	@Override
	public void delet(User user) {
		currentSession().delete(user);
		
	}

	@Override
	public User find(int id) {
		
		return currentSession().find(User.class, id);
	}

	@Override
	public List<User> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        Query<User> q=currentSession().createQuery(query);
        List<User> users=q.getResultList();
		
		return users;
	}

}
