package com.oguztasgin.service;

import com.oguztasgin.dto.request.PostCreateRequestDto;
import com.oguztasgin.dto.request.PostDeleteRequestDto;
import com.oguztasgin.dto.request.PostUpdateRequestDto;
import com.oguztasgin.dto.response.PostCreateResponseDto;
import com.oguztasgin.repository.IPostRepository;
import com.oguztasgin.repository.IUserRepository;
import com.oguztasgin.repository.entity.Post;
import com.oguztasgin.repository.entity.User;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService extends ServiceManager<Post, Long> {
    private IPostRepository postRepository;
    private IUserRepository userRepository;
    public PostService(IPostRepository postRepository, IUserRepository userRepository) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPost(Optional<Long> userId) {
        if (userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return findAll();
    }

    public Post getOnePostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            System.out.println("Post bulunamadi.");
        }
        return post.get();
    }

    public PostCreateResponseDto createPost(PostCreateRequestDto dto) {
        //We have to check if user exist
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()){
            System.out.println("Kullanici bulunamadi.");
        }
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user.get())
                .build();
        save(post);
        PostCreateResponseDto postCreateResponseDto = PostCreateResponseDto.builder()
                .username(user.get().getUsername())
                .title(dto.getTitle())
                .content(dto.getContent())
                .commentList(post.getCommentList())
                .likeList(post.getLikeList())
                .build();
        return postCreateResponseDto;
    }

    public Boolean updatePost(PostUpdateRequestDto dto) {
        //Post only update by owner
        //I know there is a shortcut for code block but its just fun :)
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()){
            System.out.println("Kullanci bulunamadi.");
        }
        for (Post post :user.get().getPostList()) {
            if (post.getId()!= dto.getPostId()){
                System.out.println("Kullanciya ait boyle bir post bulunamadi.");
            }
            else {
                post.setTitle(dto.getTitle());
                post.setContent(dto.getContent());
                update(post);
                return true;
            }
        }
        return false;
    }

    public Boolean deletePost(PostDeleteRequestDto dto) {
        //Post only delete by owner
        Optional<Post> post = postRepository.findById(dto.getPostId());
        if (post.isEmpty()){
            System.out.println("Post bulunadi");
            return false;
        }
        if (post.get().getUser().getId() != dto.getUserId()){
            System.out.println("Kullanciya ait boyle bir post bulunamadi.");
            return false;
        }
        delete(post.get());
        return true;
    }
}
