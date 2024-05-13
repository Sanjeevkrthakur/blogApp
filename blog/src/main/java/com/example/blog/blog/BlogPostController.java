package com.example.blog.blog;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogPost")
public interface BlogPostController {
    @GetMapping
    ResponseEntity<List<BlogPost>> getAllBlogPost();

    @GetMapping("/{id}")
    ResponseEntity<?> getBlogPostById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<?> updateBlogPostById(@PathVariable("id") Long id,
                                         @RequestBody BlogPostDTO blogDTO);

    @PostMapping
    ResponseEntity<?> createBlogPost(@Valid @RequestBody BlogPostDTO blogDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBlogPost(@PathVariable("id") Long id);

    @GetMapping("/search")
     ResponseEntity<?> getAllBlogPost(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );




}
