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

import com.app.DAO.BookDAO;
import com.app.pojo.Book;


@Repository("BookDAO")
@Transactional
public class BookDAOImpl implements BookDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	 protected Session currentSession() {
		 return this.sessionFactory.getCurrentSession();
	 }

	@Override
	public void create(Book book) {
		// TODO Auto-generated method stub
		currentSession().save(book);
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		currentSession().update(book);
	}

	@Override
	public void delet(Book book) {
		// TODO Auto-generated method stub
		currentSession().delete(book);
	}

	@Override
	public Book find(int id) {
		// TODO Auto-generated method stub
		return currentSession().find(Book.class, id);
	}

	@Override
	public List<Book> getall() {
		CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root);
        Query<Book> q=currentSession().createQuery(query);
        List<Book> books=q.getResultList();
		
		return books;
	}

}
