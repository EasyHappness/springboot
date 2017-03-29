package cn.no7player.config;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.serializer.UUIDCodec;

import cn.no7player.controller.TestSchedule;
import cn.no7player.controller.UserController;
import cn.no7player.model.User;
import cn.no7player.service.UserService;

/**
 * 定时任务配置类
 *
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@Scheduled(cron = "0 11 14 ? * *") // 每20秒执行一次0/20 * * * * ?     具体时间点：0 11 14 ? * *
	public void scheduler() {
		logger.info(">>>>>>>>>>>>> scheduled ... ");
		User user = new User();
    	user.setAge(13);
    	user.setName("yasong");  
    	user.setPassword("123456");
    	userService.insertUserNoback(user);
		System.out.println("++++++++++++++++++++执行");
				
	}

}
