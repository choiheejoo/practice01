package practice.practice01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.practice01.domain.Member;
import practice.practice01.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입 폼 이동
    @GetMapping("/members/join")
    public String joinForm(){
        return "members/memberJoin";
    }

    //회원 등록
    @PostMapping("/members/join")
    public String create(MemberForm form){
        Member member = new Member();

        member.setId(form.getId());
        member.setPw(form.getPw());

        memberService.join(member);
       return "redirect:/";
    }

    //회원 리스트 이동
    @GetMapping("/members")
    public String list(Model model){
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("memberList", memberList);

        return "members/memberList";
    }

}
