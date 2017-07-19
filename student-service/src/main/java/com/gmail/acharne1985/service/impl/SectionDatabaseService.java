package com.gmail.acharne1985.service.impl;

import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.dao.SectionDao;
import com.gmail.acharne1985.dao.impl.SectionDatabaseDao;
import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.service.SectionService;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class SectionDatabaseService extends AbstractService<Section> implements SectionService{

    private static Logger log = Logger.getLogger(SectionDatabaseService.class.getName());

    private SectionDao sectionDao = new SectionDatabaseDao();

    @Override
    public Section sectionJoinStudent(Integer id) {

        Section section = null;

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            section = sectionDao.getSectionsJoinStudent(id);
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

        return section;
    }

    @Override
    ItemDao<Section> getDao() {
        return sectionDao;
    }
}
