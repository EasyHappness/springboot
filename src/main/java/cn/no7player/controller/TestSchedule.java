package cn.no7player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.no7player.model.User;
import cn.no7player.service.UserService;

public class TestSchedule {

	@Autowired
	private UserService userService;
	
	public List<User> getUser(){
		List<User> list = userService.getUserInfo();
		System.out.println("=================");
		return list;
	}
}
