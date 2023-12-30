package com.hackathon.atcollabo_round2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // http://localhost:8080/hello-mvc?name=TanDat
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //http://localhost:8080/hello-api?name=TanDat
    // It coverts to JSON
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
