package cn.no7player.thread;

import org.springframework.context.ApplicationContext;

import cn.no7player.model.User;
import cn.no7player.service.UserService;

public class MyThread implements Runnable {

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MyThread(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		this.getUser(user);
		System.out.println("进入线程");
	}

	public User getUser(User use) {

		UserService userService = (UserService) SpringUtils.getBean("userService");
		User us = userService.getUserById(use.getId());
		System.out.println("user" + us.getName());
		return us;
	}

}
