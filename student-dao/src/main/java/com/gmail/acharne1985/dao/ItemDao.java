package com.gmail.acharne1985.dao;

import com.gmail.acharne1985.models.Model;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao<T extends Model> {

    //read
    List<T> getAll() throws SQLException;


    T getById(Integer id) throws SQLException;

    //create
    void add(T model) throws SQLException;

    //update
    void update(T model) throws SQLException;

    //remove
    void remove(T model) throws SQLException;
}
