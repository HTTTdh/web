package com.HTTTdh.project1.DTO;

import com.HTTTdh.project1.models.Post;

public class PostDTO {
    private String title;
    private String content;
    private Long idAuthor;
    public PostDTO() {}

    public PostDTO(Long id, String title, String content) {
        this.title = title;
        this.content = content;
        this.idAuthor = id;
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

    public static PostDTO fromEntity(Post post) {
        Long authorId = post.getAuthor() != null ? post.getAuthor().getId() : null;
        return new PostDTO(authorId, post.getTitle(), post.getContent());
    }

    public Post toEntity() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);

        return post;
    }
}
