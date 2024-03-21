package org.example.servic;

import org.example.pojo.User;

public interface UserSvice {
    User findByUserName(String username);

    void register(String username,String password);


    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
