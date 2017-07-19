package com.gmail.acharne1985.dao.impl;

import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao{

    protected Student fillingToStudent (ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setId(resultSet.getInt("ID"));
        student.setStudentName(resultSet.getString("STUDENTNAME"));
        student.setSurname(resultSet.getString("SURNAME"));
        student.setAge(resultSet.getInt("AGE"));
        student.setSex(resultSet.getString("SEX"));

        return student;
    }

    protected List<Student> fillingToStudentList (ResultSet resultSet) throws SQLException {

        List<Student> studentList = new ArrayList<>();

        while (resultSet.next()) {
            Student student = fillingToStudent(resultSet);
            studentList.add(student);
        }

        return studentList;
    }

    protected PreparedStatement fillingToStudentStatement (PreparedStatement preparedStatement, Student student) throws SQLException {

        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getStudentName());
        preparedStatement.setString(3, student.getSurname());
        preparedStatement.setInt(4, student.getAge());
        preparedStatement.setString(5, student.getSex());

        return preparedStatement;
    }

    protected PreparedStatement fillingToSectionsStatement (PreparedStatement preparedStatement, Section sections) throws SQLException {

        preparedStatement.setString(1, sections.getNameSection());
        preparedStatement.setString(2, sections.getAddress());
        preparedStatement.setInt(3, sections.getPhone());
        preparedStatement.setInt(4, sections.getStudentId());

        return preparedStatement;
    }

    protected Section fillingToSections(ResultSet resultSet) throws SQLException {

        Section sections = new Section();

        sections.setId(resultSet.getInt("ID"));
        sections.setNameSection(resultSet.getString("NAMESECTION"));
        sections.setAddress(resultSet.getString("ADDRESS"));
        sections.setPhone(resultSet.getInt("PHONE"));
        sections.setStudentId(resultSet.getInt("STUDENTID"));

        return sections;
    }

    protected List<Section> fillingToSectionsList (ResultSet resultSet) throws SQLException {
        List<Section> sectionsList = new ArrayList<>();

        while (resultSet.next()) {
            Section sections = fillingToSections(resultSet);
            sectionsList.add(sections);
        }

        return sectionsList;
    }

    protected Student toStudentWithSections(ResultSet rSet) throws SQLException {

        rSet.next();

        Student student = fillingToStudent(rSet);
        if (rSet.getString("NAMESECTION") != null) {
            List<Section> sectionsList = new ArrayList<>();
            do {
                Section sections = fillingToSections(rSet);
                sectionsList.add(sections);
            } while (rSet.next());
            student.setSectionList(sectionsList);
        }

        return student;
    }

    protected Section toSectionsWithStudent (ResultSet resultSet) throws  SQLException {

        resultSet.next();
        Section section = fillingToSections(resultSet);
        Student student = fillingToStudent(resultSet);

        section.setStudent(student);

        return section;
    }
}
