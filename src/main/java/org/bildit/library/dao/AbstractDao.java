package org.bildit.library.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ognjen Mišiæ Ova generièna klasa je bazna klasa za dao implementaciju
 *         i daje nam standardne hibernate operacije
 *
 * @param <PK>
 *            tip primarnog kljuèa
 * @param <T>
 *            objekat koji se persistuje
 */
public abstract class AbstractDao<PK extends Serializable, T> {

	@Autowired
	private SessionFactory sf;

	private final Class<T> persistentClass; // vrsta objekta koji æemo da
											// persistujemo u bazu

	// koristeæi refleksiju vidimo koja klasa zapravo trenutno ekstenduje ovu
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return sf.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllEntities() {
		// for some reason getting all entities through the criteria kept adding new users as entries?
		// this is fulthy as shit cuz i had to substring out the class name out of the package name
		return getSession().createQuery("FROM "+persistentClass.toString().substring(31)).list(); 
	}
	
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}
