package org.example.servic;

import org.example.pojo.Student;
import org.example.pojo.User;

import java.util.List;

public interface StudentSvice {
    Student findByStudentName(String studentname);

    List<Student> getAll();
}
