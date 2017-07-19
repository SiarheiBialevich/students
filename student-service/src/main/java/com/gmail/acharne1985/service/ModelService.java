package com.gmail.acharne1985.service;

import com.gmail.acharne1985.models.Model;

import java.util.List;

public interface ModelService<T extends Model> {

    List<T> readAll();

    T readById(Integer id);

    void create(T model);

    void update(T model);

    void remove(T model);

}
