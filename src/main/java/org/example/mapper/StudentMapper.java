package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.example.pojo.User;
@Mapper
public interface StudentMapper {
    @Select("select * from students where name = #{name}")
    Student findByStudentName(String name);
}
