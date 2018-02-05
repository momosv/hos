package cn.momosv.hos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@MapperScan("cn.momosv.hos.dao")
@ConfigurationProperties("classpath:application.yml") //接收application.yml中的myProps下面的属性
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class HosApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HosApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HosApplication.class, args);
	}


	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		// configurableEmbeddedServletContainer.setPort(9090);
	}
}
