package cn.momosv.hos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@MapperScan("cn.momosv.hos.dao")
@ConfigurationProperties("classpath:application.yml") //接收application.yml中的myProps下面的属性
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class HosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HosApplication.class, args);
	}
}
