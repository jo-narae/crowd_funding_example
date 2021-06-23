package com.funding.crowd.controller;

import com.funding.crowd.domain.Post;
import com.funding.crowd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") long id) {
        return postService.findById(id);
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") long id, @RequestBody Post req) {
        Post post = postService.findById(id);
        post.setPost(req.getTitle(), req.getDescription(), req.getTargetAmount(), req.getStartDate(), req.getEndDate(),
                req.getTotalAmount(), req.getParticipatingMembers());

        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") long id) {
        Post post = postService.findById(id);
        postService.delete(post);
    }
}
