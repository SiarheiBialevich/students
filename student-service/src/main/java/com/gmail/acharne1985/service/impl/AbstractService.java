package com.gmail.acharne1985.service.impl;

import com.gmail.acharne1985.connectorDb.ConnectorDb;
import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.models.Model;
import com.gmail.acharne1985.service.ModelService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract class AbstractService<T extends Model> implements ModelService<T> {

    private static Logger log = Logger.getLogger(AbstractService.class.getName());

    Connection con = ConnectorDb.getInstance().getConnection();

    @Override
    public List<T> readAll(){

        List<T> listModel = null;

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            listModel = getDao().getAll();
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction complete");
        } catch (SQLException e){

            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

            log.error(e.getMessage(), e);
        }

        return listModel;
    }

    @Override
    public T readById(Integer id) {
        T modelService = null;

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            modelService = getDao().getById(id);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction complete");
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

            log.error(e.getMessage(), e);
        }

        return modelService;
    }

    @Override
    public void create(T model) {

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            getDao().add(model);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction complete");
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(T model) {

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            getDao().update(model);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction complete");
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(T model) {

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            getDao().remove(model);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction complete");
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

            log.error(e.getMessage(), e);
        }
    }

    abstract ItemDao<T> getDao();
}
