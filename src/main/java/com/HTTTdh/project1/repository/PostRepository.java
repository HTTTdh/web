package com.HTTTdh.project1.repository;

import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
     Optional<Post> findById(Long id);
     List<Post> findByAuthor(User author);
}
