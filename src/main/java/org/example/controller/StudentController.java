package org.example.controller;

import com.jayway.jsonpath.internal.function.sequence.Last;
import jakarta.validation.constraints.Pattern;
import org.example.pojo.Category;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.example.servic.StudentSvice;
import org.example.servic.UserSvice;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/add")
    public Result add(@RequestBody Student u){

        studentSvice.add(u);
        return Result.success();
    }
    @PostMapping("/addExcel")
    public Result addExcel(@RequestBody List<Student> u){

        studentSvice.addExcel(u);
        return Result.success();
    }



    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_Pwd");                   //两次参数校验 起始
        String newPwd = params.get("new_Pwd");

        studentSvice.updataPwd(oldPwd,newPwd);
        return Result.success();
    }
    @PostMapping("/prove")
    public Result prove(@RequestBody Map<String,Integer> params){
        Integer id = params.get("studentid");
        studentSvice.prove(id);
        return Result.success();
    }
    @PostMapping("/Delet")
    public Result Delet(@RequestBody Map<String,Integer> params){
        Integer id = params.get("studentid");
        studentSvice.Delet(id);
        return Result.success();
    }





}

