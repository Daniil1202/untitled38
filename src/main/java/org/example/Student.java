package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Students")

public class Student {

    @Id
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "secondname")
    private String secondname;
    @Column(name = "age")
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getSecondName() {
        return secondname;
    }

    public void setSecondName(String secondName) {
        this.secondname = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", age=" + age +
                '}';
    }
}
