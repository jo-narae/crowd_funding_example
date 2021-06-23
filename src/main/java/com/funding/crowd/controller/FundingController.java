package com.funding.crowd.controller;

import com.funding.crowd.domain.Funding;
import com.funding.crowd.domain.Member;
import com.funding.crowd.domain.Post;
import com.funding.crowd.dto.FundingDTO;
import com.funding.crowd.service.FundingService;
import com.funding.crowd.service.MemberService;
import com.funding.crowd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fundings")
public class FundingController {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FundingService fundingService;

    @GetMapping
    public List<Funding> getFundings() {
        return fundingService.findAll();
    }

    @GetMapping("/{id}")
    public Funding getFunding(@PathVariable("id") long id) {
        return fundingService.findById(id);
    }

    @PostMapping
    public Funding saveFunding(@RequestBody FundingDTO dto) {
        Member member = memberService.findById(dto.getMemberId());
        Post post = postService.findById(dto.getPostId());

        post.setTotalAmount(post.getTotalAmount() + dto.getFundingAmount());
        post.setParticipatingMembers(post.getParticipatingMembers() + 1);

        Funding funding = new Funding(member, post, dto.getFundingAmount(), dto.getComment());

        postService.save(post);

        return fundingService.save(funding);
    }

    @PutMapping("/{id}")
    public Funding updateFunding(@PathVariable("id") long id, @RequestBody FundingDTO dto) {
        Member member = memberService.findById(dto.getMemberId());
        Post post = postService.findById(dto.getPostId());
        Funding funding = fundingService.findById(id);

        funding.setFunding(member, post,dto.getFundingAmount(), dto.getComment());

        return fundingService.save(funding);
    }

    @DeleteMapping("/{id}")
    public void deleteFunding(@PathVariable("id") long id) {
        Funding funding = fundingService.findById(id);
        fundingService.delete(funding);
    }
}
