package com.example.board.controller;

import com.example.board.entity.Post;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 목록 조회
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    // 상세 조회
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "posts/view";
    }

    // 작성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";
    }

    // 작성 처리
    @PostMapping
    public String create(@Valid @ModelAttribute Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/form";
        }
        Post saved = postService.create(post);
        return "redirect:/posts/" + saved.getId();
    }

    // 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "posts/form";
    }

    // 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Post post,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "posts/form";
        }
        postService.update(id, post);
        return "redirect:/posts/" + id;
    }

    // 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}
