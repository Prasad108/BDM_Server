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

import com.app.DAO.LanguagesDAO;
import com.app.pojo.Languages;

@Repository("LanguagesDAO")
@Transactional
public class LanguagesDAOImpl implements LanguagesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Languages lang) {
		currentSession().save(lang);

	}

	@Override
	public void update(Languages lang) {
		currentSession().update(lang);

	}

	@Override
	public Languages edit(int id) {
		
		return find(id);
	}

	@Override
	public void delet(Languages lang) {
		currentSession().delete(lang);

	}

	@Override
	public Languages find(int id) {
		return (Languages)currentSession().get(Languages.class, id);
	}

	@Override
	public List<Languages> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Languages> query = builder.createQuery(Languages.class);
        Root<Languages> root = query.from(Languages.class);
        query.select(root);
        Query<Languages> q=currentSession().createQuery(query);
        List<Languages> languages=q.getResultList();
		
		return languages;
	}

}
