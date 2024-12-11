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

    // ������ ����
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // �Խñ� ��� ��ȸ
    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    // �Խñ� �ۼ� ��
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // �Խñ� �ۼ� ó��
    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts";
    }

    // �Խñ� �� ��ȸ
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        if (post == null) {
            return "error/404";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    // �Խñ� ���� ��
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

        // ���� �Խñ� ��ȸ
        Post existingPost = postService.getPost(id);
        if (existingPost == null) {
            return "error/404";
        }

        // �ʿ��� �ʵ常 ������Ʈ
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        // createdAt �ʵ�� ����

        // ������Ʈ�� �Խñ� ����
        postService.updatePost(existingPost);

        return "redirect:/posts/" + id;
    }


    // �Խñ� ����
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
