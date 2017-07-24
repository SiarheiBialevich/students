package com.gmail.acharne1985.service.impl;

import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.dao.SectionDao;
import com.gmail.acharne1985.dao.impl.SectionDaoImpl;
import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.service.SectionService;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class SectionServiceImpl extends AbstractServiceImpl<Section> implements SectionService{

    private static Logger log = Logger.getLogger(SectionServiceImpl.class.getName());

    private SectionDao sectionDao = new SectionDaoImpl();

    @Override
    public Section sectionJoinStudent(Integer id) {

        Section section = null;

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            section = sectionDao.getSectionsJoinStudent(id, con);
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
