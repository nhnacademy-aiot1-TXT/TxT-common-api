package kr.co.contxt.commonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CommonApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApiApplication.class, args);
    }

}
