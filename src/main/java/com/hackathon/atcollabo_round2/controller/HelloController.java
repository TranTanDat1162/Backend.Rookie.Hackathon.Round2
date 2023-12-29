package com.hackathon.atcollabo_round2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // http://localhost:8080/hello
    // It returns to {data} into hello.html
    // For example: <p th:text="'Hello, ' + ${data}" ></p>
    // We create hello.html in templates, note static folder in resources folder.
    // It will return Hello Tấn Dạt!!, which Tấn Đạt!! is the value of the key "data"
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Tấn Đạt!!");
        return "hello";
    }
}
