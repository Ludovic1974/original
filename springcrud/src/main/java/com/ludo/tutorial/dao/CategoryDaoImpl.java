package com.ludo.tutorial.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ludo.tutorial.model.Category;

@Repository
public class CategoryDaoImpl implements LibraryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<?> list() {
		String sentencia;
		TypedQuery<?> query;
		sentencia = "from Category c ORDER BY c.name";
		System.out.println(sentencia);
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();
	}

	@Override
	public long num() {

		return ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Category").uniqueResult());
	}

	@Override
	public void save(@Valid Object category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);

	}

	@Override
	public void delete(long id) {
		Category savedCategoryEntity = sessionFactory.getCurrentSession().find(Category.class, id);
		sessionFactory.getCurrentSession().remove(savedCategoryEntity);

	}

	@Override
	public Category get(long id) {
		return sessionFactory.getCurrentSession().find(Category.class, id);

	}

}
