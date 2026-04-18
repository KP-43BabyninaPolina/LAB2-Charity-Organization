package ua.kpi.charity.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {
    Optional<E> find(Integer id);
    List<E> findAll();
    void create(E entity);
    void update(E entity);
    void delete(Integer id);
}