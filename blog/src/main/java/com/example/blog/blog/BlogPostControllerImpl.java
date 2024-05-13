package com.example.blog.blog;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
public class BlogPostControllerImpl implements BlogPostController {

    @Autowired
    private BlogPostService blogService;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
       return blogService.getAllBlogPost();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogPostById(@PathVariable("id") Long id) {
        return blogService.getBlogPostById(id);

    }

    @Override
    public ResponseEntity<?> updateBlogPostById(Long id, BlogPostDTO blogDTO) {
    return blogService.updateBlogPostById(id,blogDTO);
    }

    @PostMapping
    public ResponseEntity<?> createBlogPost(@Valid @RequestBody BlogPostDTO blogDTO) {
     return  blogService.saveBlogPost(blogDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlogPost(@PathVariable("id") Long id) {
       return blogService.deleteBlogPost(id);

    }

    public ResponseEntity<?> getAllBlogPost(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        //Pageable pageable = PageRequest.of(page, size);
       return blogService.getAllBlogPost(author, title, pageable);
    }



}
