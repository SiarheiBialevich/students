package com.gmail.acharne1985.service;

import com.gmail.acharne1985.models.Student;

public interface StudentService extends ModelService<Student> {

    Student studentJoinSection(Integer id);

}
