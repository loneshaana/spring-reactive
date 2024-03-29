package com.java.reactive.in.mongo.web.controller;

import com.java.reactive.in.mongo.dao.entity.Blog;
import com.java.reactive.in.mongo.services.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public Flux<Blog> findAll(){
        log.debug("Find All Blog");
        return blogService.findAll();
    }

    @GetMapping("/find")
    public Flux<Blog> findByTitle(@RequestParam String title){
        return blogService.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Mono<Blog> findOne(@PathVariable String id){
        return blogService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Blog> create(@RequestBody Blog blog) {
        log.debug("create Blog with blog : {}", blog);
        return blogService.createBlog(blog);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable String id) {
        log.debug("delete Blog with id : {}", id);
        return blogService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<Blog> updateBlog(@RequestBody Blog blog, @PathVariable String id) {
        log.debug("updateBlog Blog with id : {} and blog : {}", id, blog);
        System.out.println("Update blog with Id "+id);
        return blogService.updateBlog(blog, id);
    }

}
