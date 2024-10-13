package com.Blog.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.Entites.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

}
