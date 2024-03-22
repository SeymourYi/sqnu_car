package org.example.servic.Impl;

import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.example.servic.StudentSvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentSvice {


    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student findByStudentName(String username){
        Student u = studentMapper.findByStudentName(username);
        return u;
    }
}
