package com.oguztasgin.dto.response;
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
public class CommentResponseDto {
    private String content;
    private String username;
    private String commentDate;
    private List<Like> likeList;

}
