package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new") //화면이 보여지기 위한 GetMapping
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    @PostMapping("/members/new") //등록을 위한 PostMapping
    public String create (@Valid MemberForm form, BindingResult result){ //@Vaild는 MemberForm @NotEmpty name을 넘겨준다. Vaild의 오류는 BindingResult에 담긴다.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        if (result.hasErrors()){ //BindingResult에 담긴 result에 만약 에러가 발생한다면
            return "members/createMemberForm"; //members/createMemberForm 페이지로 반환된다.
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);


        memberService.join(member); // 호출 시 저장
        return "redirect:/"; //저장이 끝나면 어디로 갈래? - 저장된 후에는 재저장이 되면 안좋기 때문에 redirect를 form으로 보낸다
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}