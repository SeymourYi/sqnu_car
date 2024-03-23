package org.example.controller;

import com.jayway.jsonpath.internal.function.sequence.Last;
import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.example.servic.StudentSvice;
import org.example.servic.UserSvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentSvice studentSvice;
    @GetMapping("/search")
    public Result register(String name){
        Student u = studentSvice.findByStudentName(name);
        if (u != null){
            // 可以注册
            System.out.println(1);
            return Result.success(u);
        }else {

            System.out.println(2);
            return Result.error("查无此人哦❥(^_-),宝贝");
            // 不可以注册ILES HILEWH IL M
        }
    }
    @GetMapping("/getall")
    public Result<List<Student>> getall(){

        List<Student> u = studentSvice.getAll();
        return Result.success(u);
    }
}

