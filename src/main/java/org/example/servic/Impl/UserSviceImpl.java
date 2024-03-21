package org.example.servic.Impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.servic.UserSvice;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserSviceImpl implements UserSvice {
    @Autowired
    UserMapper userMapper;
    @Override
   public User findByUserName(String username){
       User u = userMapper.findByUserName(username);
       return u;
    }
    @Override
    public void register(String username,String password){
        password = Md5Util.getMD5String(password);
        userMapper.add(username,password);
    }

    @Override
    public void update(@RequestBody @Validated User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
       Integer id =(Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        System.out.println(map);                      //检查点

        Integer id =(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }


}
