package com.amadeus.tch.manager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EntityManager<T extends Serializable> {

	List<T> findAll();

	Long save(T entity);

	boolean delete(T entity);

	boolean update(T entity);

	boolean deleteAll(Collection<T> entities);

	boolean updateAll(Collection<T> entities);

	List<Long> saveAll(Collection<T> entities);

	Optional<T> findByID(Long id);

	List<T> findByIDs(Long[] id);

	List<T> findByIDs(Collection<Long> ids);

	Long count();

	void saveOrUpdate(T entity);

	void saveOrUpdateAll(Collection<T> entities);

}
