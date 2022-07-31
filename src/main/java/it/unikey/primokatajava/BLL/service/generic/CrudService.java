package it.unikey.primokatajava.BLL.service.generic;

import it.unikey.primokatajava.BLL.exceptions.NotFoundException;

import java.util.List;

public interface CrudService<T> {
    T insert(T entity);
    T getById(Integer id) throws NotFoundException;
    List<T> getAll();
    T update(T entity) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
