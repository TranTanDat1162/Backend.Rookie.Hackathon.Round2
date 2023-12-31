package com.hackathon.atcollabo_round2.controller;

import com.hackathon.atcollabo_round2.domain.Member;
import com.hackathon.atcollabo_round2.service.MemberService;
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

    /**
     * /members/new => show member register page
     */
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * member registration
     * @param memberForm
     * @return
     */
    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    /**
     * member list
      */
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
