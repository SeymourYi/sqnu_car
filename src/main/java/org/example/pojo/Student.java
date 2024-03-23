package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Student {
    @NonNull
    private Integer student_id;//主键ID 学号
    private String name;//学生姓名
    private String sex;//性别
    private String birthdate;//出生日期
    private String society_id;//身份证号
    private String major;//专业
    private String level;//层次
    private String system_type;//学制
    private String join_date;//入学时间
    private String end_date;//毕业时间
    private boolean Certification;//审核通过
    private boolean isdelete;//是否删除



}
