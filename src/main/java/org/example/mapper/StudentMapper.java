package org.example.mapper;

import com.jayway.jsonpath.internal.function.sequence.Last;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from students where name = #{name}")
    Student findByStudentName(String name);
    @Select("select * from students")
    List<Student> getAll();
}
