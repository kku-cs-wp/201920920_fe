package com.example.board.repository;

import com.example.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공
}
