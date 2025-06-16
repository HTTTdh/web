package com.HTTTdh.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createdAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonIgnore
    private User author;
}
