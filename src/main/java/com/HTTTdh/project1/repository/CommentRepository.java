package com.HTTTdh.project1.repository;

import com.HTTTdh.project1.models.Comment;
import com.HTTTdh.project1.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
