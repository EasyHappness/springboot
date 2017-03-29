package cn.no7player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getUserInfo() {
		List<User> user = userMapper.findUserInfo();
		return user;
	}

	@Transactional(rollbackFor = { IllegalArgumentException.class })
	public int insertUser(User user) {
		userMapper.insert(user);
		if ("yasong".equalsIgnoreCase(user.getName())) {
			throw new IllegalArgumentException("sang 已存在，数据将回滚");
		}
		return 0;
	}
	
	@Transactional(noRollbackFor = { IllegalArgumentException.class })
	public int insertUserNoback(User user) {
		userMapper.insert(user);
		if ("yasong".equalsIgnoreCase(user.getName())) {
			throw new IllegalArgumentException("sang 已存在，数据将bu回滚");
		}
		return 0;
	}
	
	public User getUserById(int id){
		return userMapper.getUserById(id);
	}

}
