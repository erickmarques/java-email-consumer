package br.com.erickmarques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JavaEmailConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEmailConsumerApplication.class, args);
	}

}
