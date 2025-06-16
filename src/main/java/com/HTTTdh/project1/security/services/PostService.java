package com.HTTTdh.project1.security.services;

import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepository postRepository;
    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Post save(Post post) {
        post.setCreatedAt(new Date());
        post.setAccept(false);
        return postRepository.save(post);
    }
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getPosts(User author) {
        return postRepository.findByAuthor(author);
    }
    public Post updatePost(Post post, Long postId) {
        Optional<Post> posts = postRepository.findById(postId);
        if (posts.isPresent()) {
            Post existingPost = posts.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setAccept(false);
            existingPost.setCreatedAt(new Date());
            return postRepository.save(existingPost);
        }
        else
            throw new RuntimeException("error");

    }
    public Boolean deletePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
