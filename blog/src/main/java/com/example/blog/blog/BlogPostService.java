package com.example.blog.blog;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BlogPostService {
    ResponseEntity<List<BlogPost>> getAllBlogPost();

    ResponseEntity<?> getBlogPostById(Long id);

    ResponseEntity<?> saveBlogPost(BlogPostDTO blog);

     ResponseEntity<?> deleteBlogPost(Long id);

    ResponseEntity<?> updateBlogPostById(Long id, BlogPostDTO blogDTO);

  ResponseEntity<?> getAllBlogPost(String author, String title, Pageable pageable);
}
