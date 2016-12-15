package org.nhnnext.nextstep.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@NoRepositoryBean
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface AuditingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    @PreAuthorize("hasPermission(#entity, 'write')")
    @Override
    <S extends T> S save(S entity);

    @PreFilter("hasPermission(filterTarget, 'write')")
    @Override
    <S extends T> Iterable<S> save(Iterable<S> entities);

    @PostAuthorize("hasPermission(returnObject, 'read')")
    @Override
    T findOne(ID id);

    @PostAuthorize("hasPermission(returnObject, 'read')")
    @Override
    boolean exists(ID id);

    @PostFilter("hasPermission(filterObject, 'read')")
    @Override
    Iterable<T> findAll();

    @PostFilter("hasPermission(filterObject, 'read')")
    @Override
    Iterable<T> findAll(Iterable<ID> ids);

    void delete(ID id);

    @PreAuthorize("hasPermission(#entity, 'delete')")
    @Override
    void delete(T entity);

    @PreFilter("hasPermission(filterTarget, 'delete')")
    @Override
    void delete(Iterable<? extends T> entities);
}
