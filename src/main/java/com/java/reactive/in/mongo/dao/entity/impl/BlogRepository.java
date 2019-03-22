package com.java.reactive.in.mongo.dao.entity.impl;

import com.java.reactive.in.mongo.dao.entity.Blog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog,String> {
    Flux<Blog> findByAuthor(String author);

    Flux<Blog> findByAuthorAndDeleteIsFalse(String author);

    Flux<Blog> findByTitle(String title);

    Mono<Blog> findByIdAndDeleteIsFalse(String id);

    Flux<Blog> findByTitleAndDeleteIsFalse(String title);
}
