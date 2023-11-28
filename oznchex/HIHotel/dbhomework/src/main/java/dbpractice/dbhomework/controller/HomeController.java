package dbpractice.dbhomework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
//    시작 첫 화면
    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
