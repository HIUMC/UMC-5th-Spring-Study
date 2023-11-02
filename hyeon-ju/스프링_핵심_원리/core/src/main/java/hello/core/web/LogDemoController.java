package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //주입받는 것이 아닌 찾을 수 있는 DL 기능 객체가 주입 됨

    @RequestMapping("log-demo")
    @ResponseBody //문자를 그대로 온다고 보낼 수 있음
    public String logDemo(HttpServletRequest request) throws InterruptedException { //자바에서 제공하는 표준 survlet에 의한 http-request 정보(고객 요청 정보)를 받을 수 있음
        String requestURl = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURl);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
