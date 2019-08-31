package com.amadeus.tch.manager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityManager<T extends Serializable> implements EntityManager<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractEntityManager.class);

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<T> clazz;

	@Override
	public List<T> findAll() {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(clazz);
		Root<T> root = cr.from(clazz);
		cr.select(root);
		Query<T> query = getCurrentSession().createQuery(cr);
		return query.getResultList();
	}

	@Override
	public Optional<T> findByID(Long id) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(clazz);
		Root<T> root = cr.from(clazz);
		cr.select(root).where(cb.equal(root.<Long>get("id"), id));
		Query<T> query = getCurrentSession().createQuery(cr);
		return Optional.ofNullable(query.uniqueResult());
	}

	@Override
	public List<T> findByIDs(Long[] id) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(clazz);
		Root<T> root = cr.from(clazz);
		cr.select(root).where(root.<Long>get("id").in(Arrays.asList(id)));
		Query<T> query = getCurrentSession().createQuery(cr);
		return query.getResultList();
	}

	@Override
	public List<T> findByIDs(Collection<Long> ids) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(clazz);
		Root<T> root = cr.from(clazz);
		cr.select(root).where(root.<Long>get("id").in(ids));
		Query<T> query = getCurrentSession().createQuery(cr);
		return query.getResultList();
	}

	@Override
	public Long save(T entity) {
		logger.debug("Saving new entity of type " + clazz);
		return (Long) getCurrentSession().save(entity);
	}

	@Override
	public boolean delete(T entity) {
		logger.debug("Deleting entity of type " + clazz);
		getCurrentSession().delete(entity);
		return true;
	}

	@Override
	public boolean update(T entity) {
		logger.debug("Updating entity of type " + clazz);
		getCurrentSession().update(entity);
		return true;
	}

	@Override
	public boolean deleteAll(Collection<T> entities) {
		logger.debug("Deleting list of entities of type " + clazz);
		Session session = getCurrentSession();
		entities.stream().forEach(e -> session.delete(e));
		return true;
	}

	@Override
	public List<Long> saveAll(Collection<T> entities) {
		logger.debug("Saving list of entities of type " + clazz);
		Session session = getCurrentSession();
		return entities.stream().map(e -> (Long) session.save(e)).collect(Collectors.toList());
	}

	@Override
	public boolean updateAll(Collection<T> entities) {
		logger.debug("Updating list of entities of type " + clazz);
		Session session = getCurrentSession();
		entities.stream().forEach(e -> session.update(e));
		return true;
	}

	@Override
	public void saveOrUpdate(T entity) {
		logger.debug("Saving or updating new entity of type " + clazz);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		logger.debug("Saving or updating list of entities of type " + clazz);
		Session session = getCurrentSession();
		entities.stream().forEach(e -> session.saveOrUpdate(e));
	}

	@Override
	public Long count() {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<T> root = cr.from(clazz);
		cr.select(cb.count(root));
		Query<Long> query = getCurrentSession().createQuery(cr);
		Long countResult = query.getSingleResult();
		return countResult;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void setClass(Class<T> clazz) {
		this.clazz = clazz;
	}

}
