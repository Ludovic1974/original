package com.ludo.tutorial.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludo.tutorial.dao.LibraryDao;
import com.ludo.tutorial.dao.UserDao;
import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.model.Category;
import com.ludo.tutorial.model.User;
import com.ludo.tutorial.other.Fecha;

@Service
public class ServicesImpl implements BookService, CategoryService, UserService {

	@Autowired
	@Qualifier("bookDaoImpl")
	private LibraryDao bookDao;

	@Autowired
	@Qualifier("categoryDaoImpl")
	private LibraryDao categoryDao;

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<?> listBooks() {
		return bookDao.list();
	}

	@Override
	@Transactional
	public long numBooks() {
		return bookDao.num();
	}

	@Override
	@Transactional
	public void save(Book book) {
		if (book.getId() > 0) {
			System.out.println("Actu de libro");
			book.setUpdatedAt(Fecha.getTimeStamp());
		} else {
			System.out.println("Creación de libro");
			book.setUpdatedAt(Fecha.getTimeStamp());
			book.setCreatedAt(Fecha.getTimeStamp());
		}
		bookDao.save(book);
	}

	@Override
	@Transactional
	public void deleteBook(long id) {
		bookDao.delete(id);
	}

	@Override
	@Transactional
	public Book getBook(long id) {
		Book book = (Book) bookDao.get(id);
		return book;
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listCategories() {
		return categoryDao.list();
	}

	@Override
	@Transactional
	public long numCategories() {
		return categoryDao.num();
	}

	@Override
	@Transactional
	public void save(@Valid Category category) {
		if (category.getId() > 0) {
			System.out.println("Actu de categoría");
			category.setUpdatedAt(Fecha.getTimeStamp());
		} else {
			System.out.println("Creación de categoría");
			category.setUpdatedAt(Fecha.getTimeStamp());
			category.setCreatedAt(Fecha.getTimeStamp());
		}
		categoryDao.save(category);

	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		categoryDao.delete(id);

	}

	@Override
	@Transactional
	public Category getCategory(long id) {
		// TODO Auto-generated method stub
		return (Category) categoryDao.get(id);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return (User) userDao.get(username);
	}

	@Override
	@Transactional
	public User getUserWithBooks(String username) {
		return (User) userDao.getWithBooks(username);
	}

	@Override
	@Transactional
	public void save(User user) {
		User copiaUser = null;
		// Si se trata de una creación de usuario
		if (userDao.get(user.getUsername()) == null) {
			copiaUser = new User();
			copiaUser.setEnabled(true);
			copiaUser.setCreatedAt(Fecha.getTimeStamp());
		} else {// El usuario ya existe
			copiaUser = (User) userDao.get(user.getUsername());
			copiaUser.setEnabled(user.isEnabled());
		}
		copiaUser.setUpdatedAt(Fecha.getTimeStamp());
		copiaUser.setUsername(user.getUsername());
		copiaUser.setEmail(user.getEmail().toLowerCase());
		copiaUser.setSurname(user.getSurname().toUpperCase());
		copiaUser.setName(user.getName().substring(0, 1).toUpperCase()
				+ user.getName().substring(1, user.getName().length()).toLowerCase());
		// tenemos que encriptar la contraseña
		String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
		copiaUser.setPassword(encoded);
		copiaUser.setConfirmPassword(encoded);
		userDao.save(copiaUser);

	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDao.delete(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listUsers() {
		return userDao.list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> ListWithBooks() {
		return userDao.ListWithBooks();
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional
	public long numUsers() {
		return userDao.num();
	}

	@Override
	@Transactional
	public void loanBooks(@Valid User user) {
		userDao.loanBooks(user);

	}

}
