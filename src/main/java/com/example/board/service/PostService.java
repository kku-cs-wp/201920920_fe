package com.example.board.service;

import com.example.board.model.Post;
import java.util.List;

public interface PostService {
    void createPost(Post post);
    Post getPost(Long id);
    List<Post> getAllPosts();
    void updatePost(Post post);
    void deletePost(Long id);
}
