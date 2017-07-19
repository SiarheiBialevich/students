package com.gmail.acharne1985.models;

public class Section extends Model {

    private String nameSection;
    private String address;
    private Integer phone;
    private Integer studentId;
    private Student student;

    public Section() {
        super();
    }

    public Section(Integer id) {
        super(id);
    }

    public String getNameSection() {
        return nameSection;
    }

    public void setNameSection(String nameSection) {
        this.nameSection = nameSection;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (nameSection != null ? !nameSection.equals(section.nameSection) : section.nameSection != null) return false;
        if (address != null ? !address.equals(section.address) : section.address != null) return false;
        if (phone != null ? !phone.equals(section.phone) : section.phone != null) return false;
        if (studentId != null ? !studentId.equals(section.studentId) : section.studentId != null) return false;
        return student != null ? student.equals(section.student) : section.student == null;
    }

    @Override
    public int hashCode() {
        int result = nameSection != null ? nameSection.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sections [nameSection= ");
        sb.append(nameSection);
        sb.append(", address= ");
        sb.append(address);
        sb.append(", phone= ");
        sb.append(phone);
        sb.append(", studentId= ");
        sb.append(studentId);
        sb.append(", student= ");
        sb.append(student);
        sb.append("]");

        return sb.toString();
    }
}
