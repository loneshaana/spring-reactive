package com.java.reactive.in.mongo.services;

import com.java.reactive.in.mongo.dao.entity.Blog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogService {
    Mono<Blog> createBlog(Blog blog);

    Mono<Blog> updateBlog(Blog blog,String id);

    Flux<Blog> findAll();

    Mono<Blog> findOne(String id);

    Flux<Blog> findByTitle(String title);

    Mono<Boolean> delete(String id);

}
