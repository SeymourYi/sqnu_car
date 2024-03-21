package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import org.example.validation.StateValidation;

import java.lang.annotation.*;
//123
@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)//谁提供校验规则
@Target({ElementType.FIELD,})//注解 表示 用在什么地方
@Retention(RetentionPolicy.RUNTIME)//保留到什么阶段 编译阶段还是运行阶段

//被删掉的是生命周期
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state的参数只能是已发布或者草稿";
//指定分组
    Class<?>[] groups() default {};
//负载 读取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
