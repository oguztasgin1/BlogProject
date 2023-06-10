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
public class UpdateCommentRequestDto {
    private String content;
    private Long commentId;
    private Long userId;
}
