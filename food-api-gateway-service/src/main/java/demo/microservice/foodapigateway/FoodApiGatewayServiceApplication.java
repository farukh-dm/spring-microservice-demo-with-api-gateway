package demo.microservice.foodapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class FoodApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodApiGatewayServiceApplication.class, args);
	}

}
