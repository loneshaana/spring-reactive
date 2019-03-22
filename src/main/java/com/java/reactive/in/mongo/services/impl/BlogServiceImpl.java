package com.java.reactive.in.mongo.services.impl;

import com.java.reactive.in.mongo.dao.entity.Blog;
import com.java.reactive.in.mongo.dao.entity.impl.BlogRepository;
import com.java.reactive.in.mongo.services.BlogService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Mono<Blog> createBlog(Blog blog) {
        return blogRepository.insert(blog);
    }

    @Override
    public Mono<Blog> updateBlog(Blog blog, String id) {
        return findOne(id).doOnSuccess(blog1 -> {
                blog1.setAuthor(blog.getAuthor());
                blog1.setContent(blog.getContent());
                blog1.setTitle(blog.getTitle());
                blogRepository.save(blog1).subscribe();
        });
    }

    @Override
    public Flux<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Mono<Blog> findOne(String id) {
        return blogRepository.findByIdAndDeleteIsFalse(id).
                switchIfEmpty(Mono.error(new Exception("no blog found with id "+id)));
    }

    @Override
    public Flux<Blog> findByTitle(String title) {
        return blogRepository.findByTitleAndDeleteIsFalse(title).
                switchIfEmpty(Mono.error(new Exception("no blog found with title "+title)));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return findOne(id).doOnSuccess(blog -> {
            blog.setDelete(true);
            blogRepository.save(blog).subscribe();
        }).flatMap(blog -> Mono.just(Boolean.TRUE));
    }
}
