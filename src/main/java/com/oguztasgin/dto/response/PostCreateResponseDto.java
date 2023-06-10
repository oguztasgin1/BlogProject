package com.oguztasgin.dto.response;
import com.oguztasgin.repository.entity.Comment;
import com.oguztasgin.repository.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateResponseDto {
    private String username;
    private String title;
    private String content;
    private List<Comment> commentList;
    private List<Like> likeList;
}
