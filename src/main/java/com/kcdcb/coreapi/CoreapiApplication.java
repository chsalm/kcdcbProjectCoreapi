package com.kcdcb.coreapi;

import com.kcdcb.coreapi.config.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableSwagger2
public class CoreapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreapiApplication.class, args);
	}

}
