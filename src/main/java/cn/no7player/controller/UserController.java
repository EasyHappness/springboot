package cn.no7player.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.no7player.config.BaseControllerRollBack;
import cn.no7player.config.SchedulingConfig;
import cn.no7player.model.User;
import cn.no7player.service.UserService;
import cn.no7player.thread.MyThread;

/**
 * Created by yys on 2015/8/27.
 */
@Controller
public class UserController extends BaseControllerRollBack{

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public List<User> getUserInfo(HttpSession session, HttpServletRequest request) {
        List<User> user = userService.getUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.get(0).getName());
            logger.info("user.getAge():"+user.get(0).getAge());
            session.setAttribute("user", user);
        }
        return user;
    }
    
    @RequestMapping("/addUser")
    @ResponseBody
    public void addUser(){
    	User user = new User();
    	user.setAge(13);
    	user.setName("yasong");
    	userService.insertUser(user);
    	
    	userService.insertUserNoback(user);
    
    }
    
    @RequestMapping("/addUserNo")
    @ResponseBody
    public void addUserNoBack(){
    	
    	new SchedulingConfig().scheduler();
    	User user = new User();
    	user.setAge(13);
    	user.setName("yasong");   	
    	userService.insertUserNoback(user);
    
    }
    
	@RequestMapping("/getInfoScherdul")
	@ResponseBody
	public Object getUserInfoBySchedul() {

		User user = userService.getUserById(1);
		return user;

	}
	
	@RequestMapping("/getUserByThread")
	@ResponseBody
	public Object getUserByThread(){
		User user = new User();
		user.setId(1);
		//new MyThread(user).run();
		new MyThread(user).run();
		return user;
	}
}
