package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //hello url로 오면 다음을 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }
}
