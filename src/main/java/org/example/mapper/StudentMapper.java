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
    @Insert("insert into students (studentid, name, sex,birthdate,societyid,major,level,systemtype,joindate,enddate,Certification,isdelete) values (#{studentid}, #{name}, #{sex},#{birthdate}," +
            "#{societyid}, #{major}, #{level},#{systemtype},#{joindate}, #{enddate}, #{Certification},#{isdelete})")

    void add(Student u);
    @Update("update users set password = #{newPwd}where password = #{oldPwd}")
    void updataPwd(String oldPwd, String newPwd);
    @Update("update students set Certification = true where studentid = #{params}")
    void prove(Integer params);
    @Update("update students set isdelete = true where studentid = #{params}")
    void Delet(Integer id);
}
