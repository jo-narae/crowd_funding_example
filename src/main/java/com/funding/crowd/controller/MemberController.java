package com.funding.crowd.controller;

import com.funding.crowd.domain.Member;
import com.funding.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable("id") long id) {
        return memberService.findById(id);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable("id") long id, @RequestBody Member req) {
        Member member = memberService.findById(id);
        member.setMember(req.getName(), req.getAge());
        return memberService.save(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") long id) {
        Member member = memberService.findById(id);
        memberService.delete(member);
    }
}
