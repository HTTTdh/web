package com.HTTTdh.project1.DTO;

import com.HTTTdh.project1.models.Comment;
import com.HTTTdh.project1.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private Long authorId;
    private Long postId;

    public CommentDTO(String content, Long authorId, Long postId) {
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
    }
    public static CommentDTO fromEntity(Comment comment) {
        Long authorId = comment.getAuthor().getId();
        return new CommentDTO(comment.getContent(), authorId, comment.getPost().getId());
    }

    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setContent(this.content);
        return comment;
    }
}
