package com.example.board.controller.api;

import com.example.board.model.Post;
import com.example.board.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostApiControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private PostService postService;

    private Post post1;
    private Post post2;

    @BeforeEach
    public void setup() {
        // PostService를 목(Mock) 객체로 대체
        postService = Mockito.mock(PostService.class);

        // 컨트롤러를 직접 생성하여 목 서비스 주입
        PostApiController postApiController = new PostApiController(postService);

        // MockMvc 설정 (Standalone Setup)
        mockMvc = MockMvcBuilders.standaloneSetup(postApiController).build();

        // 테스트 데이터 생성
        post1 = new Post();
        post1.setId(1L);
        post1.setAuthor("author1");
        post1.setTitle("Title 1");
        post1.setContent("Content 1");

        post2 = new Post();
        post2.setId(2L);
        post2.setAuthor("author2");
        post2.setTitle("Title 2");
        post2.setContent("Content 2");
    }

    @Test
    public void testCreatePost() throws Exception {
        Post newPost = new Post();
        newPost.setAuthor("newAuthor");
        newPost.setTitle("New Title");
        newPost.setContent("New Content");

        // 서비스 메서드 목 설정
        doNothing().when(postService).createPost(any(Post.class));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPost)))
                .andExpect(status().isCreated());
                // .andExpect(header().string("Location", containsString("/api/posts/3"))); // 컨트롤러에서 Location 헤더 설정 시 추가
    }

    @Test
    public void testUpdatePost() throws Exception {
        Long postId = 1L;
        Post updatedPost = new Post();
        updatedPost.setId(postId);
        updatedPost.setAuthor("updatedAuthor");
        updatedPost.setTitle("Updated Title");
        updatedPost.setContent("Updated Content");

        // 서비스 메서드 목 설정
        doNothing().when(postService).updatePost(any(Post.class));

        mockMvc.perform(put("/api/posts/{id}", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPost)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPost() throws Exception {
        Long postId = 1L;

        when(postService.getPost(postId)).thenReturn(post1);

        mockMvc.perform(get("/api/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(post1.getId().intValue())))
                .andExpect(jsonPath("$.author", is(post1.getAuthor())))
                .andExpect(jsonPath("$.title", is(post1.getTitle())))
                .andExpect(jsonPath("$.content", is(post1.getContent())));
              
    }

    @Test
    public void testDeletePost() throws Exception {
        Long postId = 1L;

        doNothing().when(postService).deletePost(postId);

        mockMvc.perform(delete("/api/posts/{id}", postId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPostList() throws Exception {
        List<Post> posts = Arrays.asList(post1, post2);

        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(post1.getId().intValue())))
                .andExpect(jsonPath("$[0].author", is(post1.getAuthor())))
                .andExpect(jsonPath("$[0].title", is(post1.getTitle())))
                .andExpect(jsonPath("$[0].content", is(post1.getContent())))
                .andExpect(jsonPath("$[1].id", is(post2.getId().intValue())))
                .andExpect(jsonPath("$[1].author", is(post2.getAuthor())))
                .andExpect(jsonPath("$[1].title", is(post2.getTitle())))
                .andExpect(jsonPath("$[1].content", is(post2.getContent())));
    }
}

