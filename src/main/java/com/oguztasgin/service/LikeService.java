package com.oguztasgin.service;

import com.oguztasgin.dto.request.LikeCreateForCommentRequestDto;
import com.oguztasgin.dto.request.LikeCreateForPostRequestDto;
import com.oguztasgin.repository.ICommentRepository;
import com.oguztasgin.repository.ILikeRepository;
import com.oguztasgin.repository.IPostRepository;
import com.oguztasgin.repository.IUserRepository;
import com.oguztasgin.repository.entity.Comment;
import com.oguztasgin.repository.entity.Like;
import com.oguztasgin.repository.entity.Post;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService extends ServiceManager<Like, Long> {
    private ILikeRepository likeRepository;
    private IUserRepository userRepository;
    private IPostRepository postRepository;
    private ICommentRepository commentRepository;

    public LikeService(ILikeRepository likeRepository) {
        super(likeRepository);
        this.likeRepository = likeRepository;
    }

    public Like createLikeForPost(LikeCreateForPostRequestDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Post> post = postRepository.findById(dto.getPostId());
        if (user.isPresent() && post.isPresent()){
            Like like = Like.builder()
                    .post(post.get())
                    .user(user.get())
                    .build();
            save(like);
            return like;
        }
        return null;
    }

    public Like createLikeForComment(LikeCreateForCommentRequestDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Comment> comment = commentRepository.findById(dto.getCommentId());
        if (user.isPresent() && comment.isPresent()){
            Like like = Like.builder()
                    .comment(comment.get())
                    .user(user.get())
                    .build();
            save(like);
            return like;
        }
        return null;
    }
}
