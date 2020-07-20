package demo.microservice.foodapigateway.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/home")
public class HomeController {
	
	@GetMapping(value="")
	public ResponseEntity<String> home() {
		
		StringBuilder messageBuilder = new StringBuilder("Welcome to Food API Gateway Home.");
		
		return new ResponseEntity<String>(messageBuilder.toString(), HttpStatus.OK);
		
	}

}
