package org.example.servic.Impl;

import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.example.servic.StudentSvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentSvice {


    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student findByStudentName(String username){
        Student u = studentMapper.findByStudentName(username);
        return u;
    }

    @Override
    public List<Student> getAll() {
        List<Student> u = studentMapper.getAll();
        return u;
    }
}
