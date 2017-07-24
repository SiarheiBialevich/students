package com.gmail.acharne1985.dao.impl;


import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.models.Model;

import java.sql.*;
import java.util.List;

public abstract class AbstractDaoImpl<T extends Model> implements ItemDao<T> {

    String sqlAdd;
    String sqlUpdate;
    String sqlGetAll;
    String sqlGetById;
    String sqlRemove;

    public void add(T model, Connection con) throws SQLException {

        try (PreparedStatement statement = con.prepareStatement(sqlAdd)) {
            fillingToStatement(statement, model).executeUpdate();
        }
    }

    public List<T> getAll(Connection con) throws SQLException {

        try (Statement statement = con.createStatement()) {
            ResultSet set = statement.executeQuery(sqlGetAll);

            return fillingToList(set);
        }
    }

    public T getById(Integer id, Connection con) throws SQLException {

        try (PreparedStatement statement = con.prepareStatement(sqlGetById)) {
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();
            set.next();

            return fillingTo(set);
        }
    }

    public void update(T model, Connection con) throws SQLException {

        try (PreparedStatement statement = con.prepareStatement(sqlUpdate)) {
            fillingToStatement(statement, model).executeUpdate();
        }
    }

    public void remove(T model, Connection con) throws SQLException {

        try (PreparedStatement statement = con.prepareStatement(sqlRemove)) {
            statement.setInt(1, model.getId());

            statement.executeUpdate();
        }
    }

    protected abstract T fillingTo (ResultSet resultSet) throws SQLException;

    protected abstract List<T> fillingToList(ResultSet set) throws SQLException;

    protected abstract PreparedStatement fillingToStatement (PreparedStatement statement, T model) throws SQLException;
}
