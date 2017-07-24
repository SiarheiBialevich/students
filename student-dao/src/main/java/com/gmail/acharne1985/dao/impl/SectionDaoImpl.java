package com.gmail.acharne1985.dao.impl;

import com.gmail.acharne1985.dao.SectionDao;
import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionDaoImpl extends AbstractDaoImpl<Section> implements SectionDao{

    public SectionDaoImpl() {
        super.sqlAdd = "INSERT INTO SECTIONS (NAMESECTION, ADDRESS, PHONE, STUDENTID, ID ) VALUES (?, ?, ?, ?, ?)";
        super.sqlGetAll = "SELECT * FROM SECTIONS";
        super.sqlGetById = "SELECT * FROM SECTIONS WHERE ID=?";
        super.sqlUpdate = "UPDATE SECTIONS SET NAMESECTION=?, ADDRESS=?, PHONE=?, STUDENTID=? WHERE ID=?";
        super.sqlRemove = "DELETE FROM SECTIONS WHERE ID=?";
    }

    private String sectionsJoinStudent = "SELECT * FROM SECTIONS LEFT OUTER JOIN STUDENT ON SECTIONS.STUDENTID = STUDENT.ID WHERE SECTIONS.ID = ?";

    @Override
    public Section getSectionsJoinStudent(Integer id, Connection con) throws SQLException {

        try (PreparedStatement preparedStatement = con.prepareStatement(sectionsJoinStudent)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return toSectionsWithStudent(resultSet);
        }
    }

    protected Section toSectionsWithStudent (ResultSet set) throws  SQLException {

        set.next();
        Section section = fillingTo(set);
        Student student = new StudentDaoImpl().fillingTo(set);

        section.setStudent(student);

        return section;
    }

    protected PreparedStatement fillingToStatement(PreparedStatement statement, Section model) throws SQLException {

        statement.setString(1, model.getNameSection());
        statement.setString(2, model.getAddress());
        statement.setInt(3, model.getPhone());
        statement.setInt(4, model.getStudentId());
        statement.setInt(5, model.getId());

        return statement;
    }


    @Override
    protected Section fillingTo(ResultSet resultSet) throws SQLException {

        Section sections = new Section();

        sections.setId(resultSet.getInt("ID"));
        sections.setNameSection(resultSet.getString("NAMESECTION"));
        sections.setAddress(resultSet.getString("ADDRESS"));
        sections.setPhone(resultSet.getInt("PHONE"));
        sections.setStudentId(resultSet.getInt("STUDENTID"));

        return sections;
    }

    @Override
    protected List<Section> fillingToList(ResultSet set) throws SQLException {

        List<Section> sectionsList = new ArrayList<>();

        while (set.next()) {
            Section sections = fillingTo(set);
            sectionsList.add(sections);
        }

        return sectionsList;
    }
}
