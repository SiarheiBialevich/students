package com.gmail.acharne1985.service.impl;

import com.gmail.acharne1985.connectordb.ConnectorDb;
import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.models.Model;
import com.gmail.acharne1985.service.ModelService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract class AbstractServiceImpl<T extends Model> implements ModelService<T> {

    private static Logger log = Logger.getLogger(AbstractServiceImpl.class.getName());

    Connection con = ConnectorDb.getInstance().getConnection();

    @Override
    public List<T> readAll(){

        List<T> listModel = null;

        try {
            log.info("Transaction get all started");

            con.setAutoCommit(false);
            listModel = getDao().getAll(con);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction get all complete");
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
            log.info("Transaction read by id started");

            con.setAutoCommit(false);
            modelService = getDao().getById(id, con);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction read by id complete");
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
            log.info("Transaction create started");

            con.setAutoCommit(false);
            getDao().add(model, con);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction create complete");
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
            log.info("Transaction update started");

            con.setAutoCommit(false);
            getDao().update(model, con);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction update complete");
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
            log.info("Transaction remove started");

            con.setAutoCommit(false);
            getDao().remove(model, con);
            con.commit();
            con.setAutoCommit(true);

            log.info("Transaction remove complete");
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
