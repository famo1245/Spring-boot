package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  HelloSpringApplication {

	public static void main(String[] args) {
		//tomcat webserver 자체적으로 실행
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
