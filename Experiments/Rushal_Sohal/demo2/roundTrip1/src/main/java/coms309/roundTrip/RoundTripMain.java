package coms309.roundTrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication

public class RoundTripMain {

	public static void main(String[] args) {
		SpringApplication.run(RoundTripMain.class, args);
	}
}

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to calorie counter";
    }
    
}