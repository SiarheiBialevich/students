package com.gmail.acharne1985.dao;

import com.gmail.acharne1985.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StudentDao extends ItemDao<Student>{

    //join
    Student getStudentJoinSection(Integer id, Connection con) throws SQLException;

}
