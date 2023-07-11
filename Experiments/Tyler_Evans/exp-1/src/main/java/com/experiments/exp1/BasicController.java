package com.experiments.exp1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Very basic rest contoller.
 * 
 * @author Tyler Evans
 */

@RestController
public class BasicController {
	
	@GetMapping("/")
    public String basicString() {
        return "Test string 1. <br> New line text.";
    }
}
