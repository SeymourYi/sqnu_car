package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.State;

//两个参数分别是 给那个注解提供校验参数  校验的数据类型
public class StateValidation implements ConstraintValidator<State,String> {
/**
 *
 * s
 *
 */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
      //提供校验规则
        if (value == null){
            return false;
        }
        if(value.equals("已发布") || value.equals("草稿")){
             return true;
        }

        return false;
    }
}
