package com.gmail.acharne1985.models;

import java.util.List;

public class Student extends Model {

    private String studentName;
    private String surname;
    private Integer age;
    private String sex;
    private List<Section> sectionList;

    public Student() {
        super();
    }

    public Student(Integer id) {
        super(id);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentName != null ? !studentName.equals(student.studentName) : student.studentName != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (age != null ? !age.equals(student.age) : student.age != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        return sectionList != null ? sectionList.equals(student.sectionList) : student.sectionList == null;
    }

    @Override
    public int hashCode() {
        int result = studentName != null ? studentName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (sectionList != null ? sectionList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student [studentName= ");
        sb.append(studentName);
        sb.append(", surname= ");
        sb.append(surname);
        sb.append(", age= ");
        sb.append(age);
        sb.append(", sex= ");
        sb.append(sex);
        sb.append(", sectionsList= ");
        sb.append(sectionList);
        sb.append("]");

        return sb.toString();
    }
}
