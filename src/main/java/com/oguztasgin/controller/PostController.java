package com.oguztasgin.controller;

import com.oguztasgin.dto.request.PostCreateRequestDto;
import com.oguztasgin.dto.request.PostDeleteRequestDto;
import com.oguztasgin.dto.request.PostUpdateRequestDto;
import com.oguztasgin.dto.response.PostCreateResponseDto;
import com.oguztasgin.repository.entity.Post;
import com.oguztasgin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping("/createpost")
    @CrossOrigin("*")
    public ResponseEntity<PostCreateResponseDto> createPost (@RequestBody PostCreateRequestDto dto){
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping("/getallpost")
    @CrossOrigin("*")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(postService.getAllPost(userId));
    }

    @GetMapping("/getpost{postId}")
    @CrossOrigin("*")
    public ResponseEntity<Post> getOnePost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getOnePostById(postId));
    }

    @PutMapping("/updatepost")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updatePost (@RequestBody PostUpdateRequestDto dto){
        return ResponseEntity.ok(postService.updatePost(dto));
    }

    @DeleteMapping("/deletepost")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deletePost (@RequestBody PostDeleteRequestDto dto){
        return ResponseEntity.ok(postService.deletePost(dto));
    }
}
