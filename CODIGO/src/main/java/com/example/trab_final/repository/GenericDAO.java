package com.example.trab_final.repository;

import java.util.List;

public interface GenericDAO<T> {
    public T readById(Long id);
    public List<T> readAll();
    public T create(T obj);
    public T update(T obj);
    public boolean delete(T obj);
}
