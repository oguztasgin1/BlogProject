package com.oguztasgin.repository.entity;
import com.oguztasgin.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_tbl")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String surname;
    private String birthDay;
    private String occupation;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role=ERole.USER;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Like> likeList;

}
