package com.gmail.acharne1985.dao.impl;

import com.gmail.acharne1985.dao.StudentDao;
import com.gmail.acharne1985.models.Model;
import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends AbstractDaoImpl<Student> implements StudentDao {

    public StudentDaoImpl() {
        super.sqlAdd = "INSERT INTO STUDENT (STUDENTNAME, SURNAME, AGE, SEX, ID) VALUES(?, ?, ?, ?, ?)";
        super.sqlGetAll = "SELECT * FROM STUDENT";
        super.sqlGetById = "SELECT * FROM STUDENT WHERE ID = ?";
        super.sqlUpdate = "UPDATE STUDENT SET STUDENTNAME=?, SURNAME=?, AGE=?, SEX=? WHERE ID=?";
        super.sqlRemove = "DELETE FROM STUDENT WHERE ID=?";
    }

    private final String GET_STUDENT_JOIN_SECTION =  "SELECT * FROM STUDENT LEFT OUTER JOIN SECTIONS ON SECTIONS.STUDENTID = STUDENT.ID WHERE STUDENT.ID = ?";

    @Override
    public Student getStudentJoinSection(Integer id, Connection con) throws SQLException {

        try (PreparedStatement statement = con.prepareStatement(GET_STUDENT_JOIN_SECTION)) {
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();

            return toStudentWithSections(set);
        }
    }

    protected Student toStudentWithSections(ResultSet set) throws SQLException {

        set.next();

        Student student = fillingTo(set);
        if (set.getString("NAMESECTION") != null) {
            List<Section> sectionsList = new ArrayList<>();
            do {
                Section sections = new SectionDaoImpl().fillingTo(set);
                sectionsList.add(sections);
            } while (set.next());
            student.setSectionList(sectionsList);
        }

        return student;
    }

    protected PreparedStatement fillingToStatement (PreparedStatement statement, Student model) throws SQLException {

        statement.setString(1, model.getStudentName());
        statement.setString(2, model.getSurname());
        statement.setInt(3, model.getAge());
        statement.setString(4, model.getSex());
        statement.setInt(5, model.getId());

        return statement;
    }

    @Override
    protected Student fillingTo(ResultSet set) throws SQLException {

        Student student = new Student();

        student.setId(set.getInt("ID"));
        student.setStudentName(set.getString("STUDENTNAME"));
        student.setSurname(set.getString("SURNAME"));
        student.setAge(set.getInt("AGE"));
        student.setSex(set.getString("SEX"));

        return student;
    }

    @Override
    protected List<Student> fillingToList(ResultSet set) throws SQLException {

        List<Student> studentList = new ArrayList<>();

        while (set.next()) {
            Student student = fillingTo(set);
            studentList.add(student);
        }

        return studentList;
    }


}
