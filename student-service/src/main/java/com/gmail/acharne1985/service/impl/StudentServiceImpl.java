package com.gmail.acharne1985.service.impl;

import com.gmail.acharne1985.dao.ItemDao;
import com.gmail.acharne1985.dao.StudentDao;
import com.gmail.acharne1985.dao.impl.StudentDaoImpl;
import com.gmail.acharne1985.models.Student;
import com.gmail.acharne1985.service.StudentService;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class StudentServiceImpl extends AbstractServiceImpl<Student> implements StudentService {

    private static Logger log = Logger.getLogger(StudentServiceImpl.class.getName());

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student studentJoinSection(Integer id) {

        Student student = null;

        try {
            log.info("Transaction started");

            con.setAutoCommit(false);
            student = studentDao.getStudentJoinSection(id, con);
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

        return student;
    }

    @Override
    ItemDao<Student> getDao() {
        return studentDao;
    }
}
