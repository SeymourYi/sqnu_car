package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Student {
    private Integer studentid; // 主键ID 学号
    private String name; // 学生姓名
    private String sex; // 性别
    private String birthdate; // 出生日期
    private String societyid; // 身份证号
    private String major; // 专业
    private String level; // 层次
    private String systemtype; // 学制
    private String joindate; // 入学时间
    private String enddate; // 毕业时间
    private Integer Certification; // 审核通过
    private Integer isdelete; // 是否删除

    // 构造函数和其他方法...
}