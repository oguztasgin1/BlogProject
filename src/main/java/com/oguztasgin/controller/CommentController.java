package com.oguztasgin.controller;

import com.oguztasgin.dto.request.CreateCommentRequestDto;
import com.oguztasgin.dto.request.UpdateCommentRequestDto;
import com.oguztasgin.dto.response.CommentResponseDto;
import com.oguztasgin.repository.entity.Comment;
import com.oguztasgin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/createcomment")
    @CrossOrigin("*")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequestDto dto){
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @GetMapping("/getallcomments")
    @CrossOrigin("*")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return ResponseEntity.ok(commentService.getAllComments(userId, postId));
    }

    @PutMapping("/updatecomment")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateComment(@RequestBody UpdateCommentRequestDto dto){
        return ResponseEntity.ok(commentService.updateComment(dto));
    }

    @DeleteMapping("/deletecomment")
    @CrossOrigin("*")
    public void deleteComment(Long commentId){
        commentService.deleteById(commentId);
    }

}
