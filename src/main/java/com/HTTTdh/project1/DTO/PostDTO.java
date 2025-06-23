package com.HTTTdh.project1.DTO;

import com.HTTTdh.project1.models.Post;

import java.util.Date;

public class PostDTO {
    private String title;
    private String content;
    private Long idAuthor;
    private Date createdAt ;
    public PostDTO() {}

    public PostDTO(Long id, String title, String content, Date createdAt) {
        this.title = title;
        this.content = content;
        this.idAuthor = id;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static PostDTO fromEntity(Post post) {
        Long authorId = post.getAuthor() != null ? post.getAuthor().getId() : null;
        return new PostDTO(authorId, post.getTitle(), post.getContent(), post.getCreatedAt());
    }

    public Post toEntity() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setCreatedAt(this.createdAt);
        return post;
    }
}
