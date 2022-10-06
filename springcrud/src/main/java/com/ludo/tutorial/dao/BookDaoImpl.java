package com.ludo.tutorial.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.model.Category;

@Repository
public class BookDaoImpl implements LibraryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<?> list() {
		String sentencia;
		TypedQuery<?> query;
		sentencia = "from Book b ORDER BY b.title";
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();
	}

	@Override
	public long num() {
		long count = ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Book")
				.uniqueResult());
		return count;
	}

	@Override
	public void save(Object book) {
		Category category = null;
		Book libro = (Book) book;
		String categoryName = libro.getCategory().getName();
		category = (Category) sessionFactory.getCurrentSession()
				.createQuery("from Category c where c.name = :categoria_enviada")
				.setParameter("categoria_enviada", categoryName).uniqueResult();

		if (category != null) {
			libro.setCategory(category);
			category.getBooks().add(libro);
			if (libro.getId() > 0) {
				sessionFactory.getCurrentSession().merge(category);
			} else {
				sessionFactory.getCurrentSession().persist(category);
			}

		}
	}

	@Override
	public void delete(long id) {
		Book savedBookEntity = sessionFactory.getCurrentSession().find(Book.class, id);
		sessionFactory.getCurrentSession().remove(savedBookEntity);

	}

	@Override
	public Book get(long id) {
		return sessionFactory.getCurrentSession().get(Book.class, id);
	}

}
