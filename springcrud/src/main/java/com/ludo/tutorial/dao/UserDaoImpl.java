package com.ludo.tutorial.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.model.User;
import com.ludo.tutorial.other.Fecha;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Object user) {
		if (get(((User) user).getUsername()) == null) {
			sessionFactory.getCurrentSession().persist(user);
		} else {
			sessionFactory.getCurrentSession().merge(user);
		}

	}

	@Override
	public List<?> list() {
		String sentencia;
		TypedQuery<?> query;
		sentencia = "from User u ORDER BY u.username";
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();
	}

	@Override
	public List<?> ListWithBooks() {
		String sentencia;
		TypedQuery<?> query;
		sentencia = "SELECT distinct user from User user left outer join fetch user.books books ORDER BY user.username";
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();

	}

	@Override
	public void delete(String username) {
		User savedUserEntity = sessionFactory.getCurrentSession().find(User.class, username);
		sessionFactory.getCurrentSession().remove(savedUserEntity);

	}

	@Override
	public User get(String username) {
		return sessionFactory.getCurrentSession().find(User.class, username);
	}

	@Override
	public long num() {
		return ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM User").uniqueResult());

	}

	@Override
	public User getWithBooks(String username) {
		User user = null;
		user = (User) sessionFactory.getCurrentSession().createQuery(
				"SELECT user FROM User user left join fetch user.books books where user.username = :username ORDER BY books.title asc")
				.setParameter("username", username).uniqueResult();

		if (user == null) {
			user = sessionFactory.getCurrentSession().find(User.class, username);
		}
		return user;
	}

	@Override
	public void loanBooks(User user) {
		// Qué lista tenemos ?
		// System.out.println(user.getBooks());
		// Metemos la lista en una provisional
		List<Book> listaEnviada = user.getBooks();
		// Borramos la lista anterior
		// Eso provoca que se borrará los registros de la BDD
		user.setBooks(new ArrayList<>());
		// Cargamos los libros enviados en una nueva lista
		for (Book book : listaEnviada) {
			// Por cada elemento enviado en el form
			// hacemos una frase que usaremos para la consulta
			String sentencia = "from Book book where book.title = :title";
			// Cargamos la session factory y a base de la frase, hacemos una consulta
			// para sacar un único libro
			Book loanedBookEntity = (Book) sessionFactory.getCurrentSession().createQuery(sentencia)
					.setParameter("title", book.getTitle()).uniqueResult();
			// Añadimos el libro a la lista de libros que tiene nuestro usuario
			user.getBooks().add(loanedBookEntity);
		}
		// Grabamos
		user.setUpdatedAt(Fecha.getTimeStamp());
		sessionFactory.getCurrentSession().merge(user);
		// Sincronizamos la BDD
		sessionFactory.getCurrentSession().flush();
	}

}
