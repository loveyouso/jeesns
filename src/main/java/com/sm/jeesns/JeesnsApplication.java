package com.sm.jeesns;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sm.jeesns.dao")
@ComponentScan(basePackages ={"com.sm.jeesns"})
@ServletComponentScan
public class JeesnsApplication {

    private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(JeesnsApplication.class, args);
	}


}

