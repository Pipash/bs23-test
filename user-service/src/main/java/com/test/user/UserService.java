package com.test.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;


//@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement // to use @transactional
@EnableSwagger2WebFlux
public class UserService {

	public static void main(String[] args) {
		SpringApplication.run(UserService.class, args);
	}

}
