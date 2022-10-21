package com.ludo.tutorial.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ludo.tutorial.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Role role) {
		sessionFactory.getCurrentSession().persist(role);

	}

	@Override
	public long numUserRole(String username) {
		long result = 0;
		result = ((long) sessionFactory.getCurrentSession()
				.createQuery("SELECT count(*) FROM Role role where role.user.username = :username")
				.setParameter("username", username).uniqueResult());
		return result;
	}

}
