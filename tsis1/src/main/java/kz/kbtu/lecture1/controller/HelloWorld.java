package kz.kbtu.lecture1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "course", "Spring Boot",
                "year", "2025"
        );
    }

    @PostMapping("/calculate/sub")
    public int calculateSub(@RequestParam int x, @RequestParam int y) {
        return x - y;
    }

    @PostMapping("/calculate/add")
    public int calculateAdd(@RequestParam int x, @RequestParam int y) {
        return x + y;
    }

    @PostMapping("/calculate/mul")
    public int calculateMul(@RequestParam int x, @RequestParam int y) {
        return x * y;
    }

    @PostMapping("/calculate/div")
    public int calculate(@RequestParam int x, @RequestParam int y) {
        return x / y;
    }


}
