package cn.momosv.hos;

import cn.momosv.hos.util.DatePattern;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling//定时任务
@EnableAsync // 开启异步任务支持
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

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(source.matches(DatePattern.DATE_ONLY.getRegex())){
					sdf =new SimpleDateFormat(DatePattern.DATE_ONLY.getPattern());
				}else if(source.matches(DatePattern.YEAR_MONTH.getRegex())){
					sdf =new SimpleDateFormat(DatePattern.DATE_ONLY.getPattern());
				}

				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}
}
