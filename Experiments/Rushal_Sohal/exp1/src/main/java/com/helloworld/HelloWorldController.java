package com.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorldController {
	
	@GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

}
