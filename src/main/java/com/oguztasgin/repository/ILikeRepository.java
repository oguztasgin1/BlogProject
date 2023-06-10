package com.oguztasgin.repository;

import com.oguztasgin.repository.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepository extends JpaRepository<Like,Long> {
}
