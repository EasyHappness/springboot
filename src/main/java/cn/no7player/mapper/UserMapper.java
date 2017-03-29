package cn.no7player.mapper;

import java.util.List;

import cn.no7player.model.User;

public interface UserMapper {
    public List<User> findUserInfo();
    
    public int insert(User user);
    
    public User getUserById(int id);
}
