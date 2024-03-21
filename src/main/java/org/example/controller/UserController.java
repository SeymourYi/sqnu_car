package org.example.controller;
import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.servic.UserSvice;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//各个请求方式分别什么意思
//怎么测试啊 单元测试 参数那种
//springboot到底怎么测试 断点调试 进入函数 测试变量 什么的
//群里佬说 Java是编译性语言 不是python那种解释性语言 想屁吃呢
//檀木说可以 查询别人的文章列表 确实是哥bug
//传参的时候什么时候用parser requestbody 或者无注解的。
//@AllArgsConstructor
//@NoArgsConstructor这两个很有意思 有机会 研究研究
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
     @Autowired
    private UserSvice userSvice;
     @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
       User u = userSvice.findByUserName(username);
         if (u == null){
             // 可以注册
             userSvice.register(username,password);
             return Result.success();
         }else {
             return Result.error("此昵称已经被注册");
             // 不可以注册
         }

     }
     @PostMapping("/login")
     public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
         User loginuser = userSvice.findByUserName(username);

         if (loginuser == null){
             // 无法登录
             return Result.error("账号错误");
           }else {
             if (Md5Util.getMD5String(password).equals(loginuser.getPassword())) {
                 Map<String,Object> claims = new HashMap<>();
                 claims.put("id",loginuser.getId());
                 claims.put("username",loginuser.getUsername());
                 String token = JwtUtil.gentoken(claims);
               ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
                operations.set(token,token,1, TimeUnit.HOURS);
                 return Result.success(token);
             }
         }

         return Result.error("密码错误");
     }
     @PutMapping("/update")
     public Result update(@RequestBody User user){
         userSvice.update(user);
         return Result.success();
     }
     @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//         Map<String,Object> map = JwtUtil.parseToken(token);
//         System.out.println(map);
//         String username = (String) map.get("username");
//         System.out.println(username);

         Map<String,Object> map = ThreadLocalUtil.get();
         String username = (String) map.get("username");
         User user = userSvice.findByUserName(username);
         //使用工具类进行token验证
         ThreadLocalUtil.get();
         return Result.success(user);

     }
     @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
       Map<String,Object> map = ThreadLocalUtil.get();

         userSvice.updateAvatar(avatarUrl);
         return Result.success();
     }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
         String oldPwd = params.get("old_pwd");                   //两次参数校验 起始
         String newPwd = params.get("new_pwd");
         String rePwd = params.get("re_pwd");
         System.out.println(params);                      //检查点
         if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd) ){
          return Result.error("缺少必要的参数");
         }
         Map<String,Object> map = ThreadLocalUtil.get();
        System.out.println(map);                      //检查点

         String username = (String) map.get("username");
         System.out.println(username);                      //检查点

         User loginuser = userSvice.findByUserName(username);
        System.out.println(loginuser);                      //检查点
        if(!loginuser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return  Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)){
            return  Result.error("两次填写的新密码不一样");
        }
        System.out.println(newPwd);                      //检查点

        userSvice.updatePwd(newPwd);
        //删除redis 中的内容
       ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
       operations.getOperations().delete(token);
       // 结束
        return Result.success();
    }

}
