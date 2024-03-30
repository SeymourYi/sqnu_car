package org.example.mapper;

import com.jayway.jsonpath.internal.function.sequence.Last;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Student;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from students where name = #{name}")
    Student findByStudentName(String name);
    @Select("select * from students")
    List<Student> getAll();
    @Insert("insert into students (student_id, name, sex,birthdate,society_id,major,level,system_type,join_date,end_date,Certification,isdelete) values (#{student_id}, #{name}, #{sex},#{birthdate}," +
            "#{society_id}, #{major}, #{level},#{system_type},#{join_date}, #{end_date}, #{Certification},#{isdelete})")

    void add(Student u);
    @Update("update users set password = #{newPwd}where password = #{oldPwd}")
    void updataPwd(String oldPwd, String newPwd);
}
