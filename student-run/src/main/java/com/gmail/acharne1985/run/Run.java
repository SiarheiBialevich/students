package com.gmail.acharne1985.run;

import com.gmail.acharne1985.connectordb.ConnectorDb;
import com.gmail.acharne1985.models.Section;
import com.gmail.acharne1985.models.Student;
import com.gmail.acharne1985.service.SectionService;
import com.gmail.acharne1985.service.StudentService;
import com.gmail.acharne1985.service.impl.SectionServiceImpl;
import com.gmail.acharne1985.service.impl.StudentServiceImpl;

public class Run {

    private static SectionService sectionService = new SectionServiceImpl();
    private static StudentService studentService = new StudentServiceImpl();

    public static void main(String[] args) {

        createStudent();
        System.out.println();

        createSection();
        System.out.println();

        readAll();
        System.out.println();

        readById();
        System.out.println();

        updateStudent();
        System.out.println();

        updateSection();
        System.out.println();

        deleteStudent();
        System.out.println();

        deleteSection();
        System.out.println();

        joinStudent();
        System.out.println();

        joinSection();
        System.out.println();

        ConnectorDb.getInstance().closeConnection();
    }

    private static void createStudent() {

        Student student = new Student();

        student.setId(6);
        student.setStudentName("Victor");
        student.setSurname("Petrenko");
        student.setAge(12);
        student.setSex("male");

        studentService.create(student);
    }

    private static void createSection() {

        Section section = new Section();

        section.setId(6);
        section.setNameSection("Racing");
        section.setAddress("Kosmonavtov, 25");
        section.setPhone(375262);
        section.setStudentId(6);

        sectionService.create(section);
    }

    private static void readAll() {

        System.out.println(sectionService.readAll());
        System.out.println();
        System.out.println(studentService.readAll());
    }

    private static void readById() {

        System.out.println(sectionService.readById(6));
        System.out.println();
        System.out.println(studentService.readById(6));
    }

    private static void updateStudent() {

        Student student = studentService.readById(6);
        student.setAge(18);
        studentService.update(student);
    }

    private static void updateSection() {

        Section section = sectionService.readById(6);
        section.setNameSection("Ping Pong");
        sectionService.update(section);
    }

    private static void deleteStudent() {

        Student student = studentService.readById(6);
        studentService.remove(student);
    }

    private static void deleteSection() {

        Section section = sectionService.readById(6);
        sectionService.remove(section);
    }

    private static void joinSection() {

        System.out.println(studentService.studentJoinSection(1));
    }

    private static void joinStudent() {

        System.out.println(sectionService.sectionJoinStudent(1));
    }

}
