package com.HTTTdh.project1.DTO;

import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<PostDTO> posts;
    private String avatar;
    public UserDTO() {}

    public UserDTO(Long id, String username, String email, List<PostDTO> posts, String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.posts = posts;
        this.avatar = avatar;
    }

    // Getters & Setters

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    // Hàm chuyển từ User -> UserDTO
    public static UserDTO fromEntity(User user) {
        List<PostDTO> postDTOs = user.getPosts().stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());

        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), postDTOs, user.getAvatar());
    }

    // (Không bắt buộc) Nếu bạn cần chuyển từ DTO ngược lại sang Entity:
    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setEmail(this.email);
        return user;
    }
}
