package org.nhnnext.nextstep.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public abstract class AbstractRepositoryTest<T, R extends CrudRepository> extends AbstractIntegrationTestWithUser {

    @Autowired
    protected R repository;

    protected void initRepository() {
        repository.deleteAll();
    }

    @SuppressWarnings("unchecked")
    protected T save(T entity) {
        return (T) repository.save(entity);
    }

    @SuppressWarnings("unchecked")
    protected void delete(T entity) {
        repository.delete(entity);
    }

    @SuppressWarnings("unchecked")
    protected T findOne(Serializable id) {
        return (T) repository.findOne(id);
    }

    @SuppressWarnings("unchecked")
    protected Iterable<T> findAll() {
        return repository.findAll();
    }
}
