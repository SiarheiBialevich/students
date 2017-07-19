package com.gmail.acharne1985.dao.impl;

import com.gmail.acharne1985.connectorDb.ConnectorDb;
import com.gmail.acharne1985.dao.SectionDao;
import com.gmail.acharne1985.models.Section;

import java.sql.*;
import java.util.List;

public class SectionDatabaseDao extends AbstractDao implements SectionDao{

    private Connection con = ConnectorDb.getInstance().getConnection();

    @Override
    public void add(Section section) throws SQLException {

        String sql = "INSERT INTO SECTIONS (NAMESECTION, ADDRESS, PHONE, STUDENTID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pStatement = con.prepareStatement(sql)) {
            fillingToSectionsStatement(pStatement, section).executeUpdate();
        }
    }

    @Override
    public List<Section> getAll() throws SQLException {

        String sql = "SELECT * FROM SECTIONS";

        try ( Statement statement = con.createStatement()) {
            ResultSet rSet = statement.executeQuery(sql);

            return fillingToSectionsList(rSet);
        }
    }

    @Override
    public Section getById(Integer id) throws SQLException {

        String sql = "SELECT * FROM SECTIONS WHERE ID=?";

        try (PreparedStatement pStatement = con.prepareStatement(sql)) {

            pStatement.setInt(1, id);

            ResultSet resultSet = pStatement.executeQuery();
            resultSet.next();

            return fillingToSections(resultSet);
        }
    }

    @Override
    public void update(Section section) throws SQLException {

        String sql = "UPDATE SECTIONS SET NAMESECTIONS=?, ADDRESS=?, PHONE=?, STUDENTID=? WHERE ID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            fillingToSectionsStatement(preparedStatement, section).executeUpdate();

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(Section section) throws SQLException {

        String sql = "DELETE FROM SECTIONS WHERE id=?";

        try (PreparedStatement pStatement = con.prepareStatement(sql)) {

            pStatement.setInt(1, section.getId());

            pStatement.executeUpdate();
        }
    }

    @Override
    public Section getSectionsJoinStudent(Integer id) throws SQLException {

        String sql = "SELECT * FROM SECTIONS LEFT OUTER JOIN STUDENT ON SECTIONS.STUDENTID = STUDENT.ID WHERE SECTIONS.ID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return toSectionsWithStudent(resultSet);
        }
    }
}
