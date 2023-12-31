package com.oguztasgin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCommentRequestDto {
    private Long postId;
    private Long userId;
    private String content;
}
