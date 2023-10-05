package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    //회원가입 폼
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //회원가입
    @PostMapping("/members/new")
    public String createForm(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.print(member);
        memberService.join(member);

        return "redirect:/";
    }

    //회원 리스트 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        
        //HTML에 뿌려줄 데이터 model에 담기
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
