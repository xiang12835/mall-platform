package online.flyingfish.api.service.impl;

import java.util.Collections;
import java.util.List;

import online.flyingfish.api.model.User;
import online.flyingfish.api.mapper.UserMapper;
import online.flyingfish.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void delUserById(int no) {
        userMapper.delUserById(no);
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user.getName(),user.getEmail(),user.getId());
    }

    @Override
    public List<User> getUser() {

        List<User> userList = userMapper.getUser();

        // lamdba表达式的使用

        // List<User> userListOrderBy= userList.stream().filter((User u) -> u.getName() == "1212").collect(Collectors.toList());

        Collections.sort(userList,(user1, user2) -> user1.getId() - user2.getId());

        return userList;
    }

    @Override
    public String getUserName(int id) {
        return userMapper.getUserName(id);
    }

}
