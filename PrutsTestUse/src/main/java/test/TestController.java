package test;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.request.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }
}
