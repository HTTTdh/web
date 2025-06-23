package com.HTTTdh.project1.security.services;

import com.HTTTdh.project1.DTO.CommentDTO;
import com.HTTTdh.project1.models.Comment;
import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.repository.CommentRepository;
import com.HTTTdh.project1.repository.PostRepository;
import com.HTTTdh.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    public Comment saveComment(Comment comment, Long postId) {
        comment.setCreatedAt(new Date());
        comment.setPost(postRepository.findById(postId).get());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentByPostId(Long postId) {
        Post post = postRepository.findById(postId).get();
        List<Comment> comments = commentRepository.findByPost(post);
        return comments;
    }

    public Boolean deleteCommentById(Long postId) {
        try {
            List<Comment> comments = getCommentByPostId(postId);
            if (comments != null && !comments.isEmpty()) {
                commentRepository.deleteAll(comments);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
