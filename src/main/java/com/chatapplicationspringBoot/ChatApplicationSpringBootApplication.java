package com.chatapplicationspringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class ChatApplicationSpringBootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ChatApplicationSpringBootApplication.class, args);
	}

//	@Bean
//	public Docket swaggerDemoApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.chatapplicationspringBoot")).build();
//	}



}
