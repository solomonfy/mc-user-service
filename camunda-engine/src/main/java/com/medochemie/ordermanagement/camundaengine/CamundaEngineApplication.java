package com.medochemie.ordermanagement.camundaengine;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
//@EnableEurekaClient
public class CamundaEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaEngineApplication.class, args);
	}

}
