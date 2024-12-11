package com.example.board.controller.web;

import com.example.board.model.Post;
import com.example.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 생성자 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록 조회
    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    // 게시글 작성 폼
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // 게시글 작성 처리
    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts";
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        if (post == null) {
            return "error/404";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    // 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        if (post == null) {
            return "error/404";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/edit";
        }

        // 기존 게시글 조회
        Post existingPost = postService.getPost(id);
        if (existingPost == null) {
            return "error/404";
        }

        // 필요한 필드만 업데이트
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        // createdAt 필드는 유지

        // 업데이트된 게시글 저장
        postService.updatePost(existingPost);

        return "redirect:/posts/" + id;
    }


    // 게시글 삭제
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
