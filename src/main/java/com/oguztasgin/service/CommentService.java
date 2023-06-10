package com.oguztasgin.service;


import com.oguztasgin.dto.request.CreateCommentRequestDto;
import com.oguztasgin.dto.request.UpdateCommentRequestDto;
import com.oguztasgin.dto.response.CommentResponseDto;
import com.oguztasgin.repository.ICommentRepository;
import com.oguztasgin.repository.IPostRepository;
import com.oguztasgin.repository.IUserRepository;
import com.oguztasgin.repository.entity.Comment;
import com.oguztasgin.repository.entity.Post;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.utility.ServiceManager;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService extends ServiceManager<Comment, Long> {
    private ICommentRepository commentRepository;
    private IUserRepository userRepository;
    private IPostRepository postRepository;

    public CommentService(ICommentRepository commentRepository, IUserRepository userRepository
            , IPostRepository postRepository){
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public List<CommentResponseDto> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = findAll();
        return comments.stream().map(comment -> {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .content(comment.getContent())
                    .username(comment.getUser().getUsername())
                    .commentDate(comment.getCreatedate().toString())
                    .build();
            return commentResponseDto;
        }).collect(Collectors.toList());
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Post> post = postRepository.findById(dto.getPostId());
        if (user.isPresent() && post.isPresent()){
            Comment comment = Comment.builder()
                    .content(dto.getContent())
                    .user(user.get())
                    .post(post.get())
                    .build();
            save(comment);
            return comment;
        }
        System.out.println("Kullanci ya da Post bulunamadi.");
        return null;
    }

    //Comment only update by owner
    public Boolean updateComment(UpdateCommentRequestDto dto) {
        Optional<Comment> comment = commentRepository.findById(dto.getCommentId());
        if (comment.isEmpty()){
            System.out.println("Comment bulunamadi");
            return false;
        }
        if (comment.get().getUser().getId() != dto.getUserId()){
            System.out.println("Kullanciya ait boyle bir comment bulunamadi");
            return false;
        }
        update(Comment.builder()
                .content(dto.getContent())
                .build());
        return true;
    }
}
