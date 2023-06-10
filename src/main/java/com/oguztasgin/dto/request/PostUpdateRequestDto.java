package com.oguztasgin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostUpdateRequestDto {
    private Long userId;
    private Long postId;
    private String title;
    private String content;

}
