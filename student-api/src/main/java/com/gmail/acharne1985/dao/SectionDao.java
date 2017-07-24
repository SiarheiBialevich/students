package com.gmail.acharne1985.dao;

import com.gmail.acharne1985.models.Section;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SectionDao extends ItemDao<Section> {

    //join
    Section getSectionsJoinStudent(Integer id, Connection con) throws SQLException;

}
