package cn.no7player;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.no7player.thread.SpringUtils;

@Controller
@EnableWebMvc
@SpringBootApplication
public class Application {
    private static Logger logger = Logger.getLogger(Application.class);
    
    public static void main(String[] args) {
    	ConfigurableApplicationContext ac = SpringApplication.run(Application.class, args);
    	SpringUtils.setApplicationContext(ac);
        logger.info("SpringBoot Start Success");
    }

}
