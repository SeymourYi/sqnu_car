package org.example.servic.Impl;

import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.example.servic.StudentSvice;
import org.example.utils.Md5Util;
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

    @Override
    public void add(Student u) {
        System.out.println(u);
         studentMapper.add(u);
    }

    @Override
    public void updataPwd(String oldPwd, String newPwd) {
        oldPwd = Md5Util.getMD5String(oldPwd);
        newPwd = Md5Util.getMD5String(newPwd);
        studentMapper.updataPwd(oldPwd,newPwd);
    }

    @Override
    public void prove(Integer params) {
         studentMapper.prove(params);
    }

    @Override
    public void Delet(Integer id) {
        studentMapper.Delet(id);
    }

    @Override
    public void addExcel(List<Student> u) {
        for (int i = 0; i < u.size(); i++) {
//           Object  a=u.get(i);
           studentMapper.add(u.get(i));
//            System.out.println(u.get(i).name);
        }
    }
}
