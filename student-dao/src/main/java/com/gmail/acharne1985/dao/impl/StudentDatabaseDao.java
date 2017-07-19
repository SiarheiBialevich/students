package com.gmail.acharne1985.dao.impl;

import com.gmail.acharne1985.connectorDb.ConnectorDb;
import com.gmail.acharne1985.dao.StudentDao;
import com.gmail.acharne1985.models.Student;

import java.sql.*;
import java.util.List;

public class StudentDatabaseDao extends AbstractDao implements StudentDao {

    private Connection con = ConnectorDb.getInstance().getConnection();

    @Override
    public void add(Student student) throws SQLException {

        String sql = "INSERT INTO STUDENT (STUDENTID, STUDENTNAME, SURNAME, AGE, SEX) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            fillingToStudentStatement(preparedStatement, student).executeUpdate();
        }
    }

    @Override
    public List<Student> getAll() throws SQLException {

        String sql = "SELECT * FROM STUDENT";

        try (Statement statement = con.createStatement()) {
            ResultSet rSet = statement.executeQuery(sql);

            return fillingToStudentList(rSet);
        }
    }

    @Override
    public Student getById(Integer id) throws SQLException {

        String sql = "SELECT * FROM STUDENT WHERE ID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return fillingToStudent(resultSet);
        }
    }

    @Override
    public void update(Student student) throws SQLException {

        String sql = "UPDATE STUDENT SET STUDENTNAME=?, SURNAME=?, AGE=?, SEX=? WHERE ID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            fillingToStudentStatement(preparedStatement, student).executeUpdate();
        }
    }

    @Override
    public void remove(Student student) throws SQLException {

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM STUDENT WHERE id=?";

        try (PreparedStatement pStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Student getStudentJoinSection(Integer id) throws SQLException {

        String sql = "SELECT * FROM STUDENT LEFT OUTER JOIN SECTIONS ON SECTIONS.STUDENTID = STUDENT.ID WHERE STUDENT.ID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return toStudentWithSections(resultSet);
        }
    }
}
