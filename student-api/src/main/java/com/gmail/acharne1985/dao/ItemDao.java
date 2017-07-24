package com.gmail.acharne1985.dao;

import com.gmail.acharne1985.models.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao<T extends Model> {

    //read
    List<T> getAll(Connection con) throws SQLException;


    T getById(Integer id, Connection con) throws SQLException;

    //create
    void add(T model, Connection con) throws SQLException;

    //update
    void update(T model, Connection con) throws SQLException;

    //remove
    void remove(T model, Connection con) throws SQLException;
}
