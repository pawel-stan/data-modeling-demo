package edu.logintegra.datamodelingdemo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO!";
    }

    @GetMapping("/repeat")
    public String repeat(@RequestParam String word) {
        return "Powiedziałeś: " + word;
    }

    @GetMapping("/up/{word}")
    public WordHelper up(@PathVariable String word) {
        return new WordHelper(word, word.toUpperCase());
    }
}
