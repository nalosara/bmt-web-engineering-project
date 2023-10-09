package ba.ibu.edu.bemytech.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "Hello, " + name + "!";
    }
}
