package com.oguztasgin.controller;

import com.oguztasgin.dto.request.LikeCreateForCommentRequestDto;
import com.oguztasgin.dto.request.LikeCreateForPostRequestDto;
import com.oguztasgin.repository.entity.Like;
import com.oguztasgin.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private LikeService likeService;

    @PostMapping("/createlikeforpost")
    @CrossOrigin("*")
    public ResponseEntity<Like> createLikeForPost(@RequestBody LikeCreateForPostRequestDto dto){
        return ResponseEntity.ok(likeService.createLikeForPost(dto));
    }

    @PostMapping("/createlikeforcomment")
    @CrossOrigin("*")
    public ResponseEntity<Like> createLikeForComment(@RequestBody LikeCreateForCommentRequestDto dto){
        return ResponseEntity.ok(likeService.createLikeForComment(dto));
    }

    @DeleteMapping("/deletelike")
    public void deleteOneLikeById(Long likeId) {
        likeService.deleteById(likeId);
    }

}
