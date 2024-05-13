package com.example.blog.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogRepository;

    @Override
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
        List<BlogPost> blogList = blogRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(blogList);
    }

    @Override
    public ResponseEntity<?> getBlogPostById(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("blog id required !!");
        }
        Optional<BlogPost> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(blog);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("blog not found !!");
        }
    }

    @Override
    public ResponseEntity<?> saveBlogPost(BlogPostDTO blogDTO) {
        BlogPost blog = new BlogPost();
        blog.setAuthor(blogDTO.getAuthor());
        blog.setContent(blogDTO.getContent());
        blog.setTitle(blogDTO.getTitle());
        blog.setCreatedAt(new Date());
        blogRepository.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @Override
    public ResponseEntity<?> deleteBlogPost(Long id) {
        Optional<BlogPost> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            blogRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Blog deleted successfully !! ");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("blog not found !!");
        }
    }

    @Override
    public ResponseEntity<?> updateBlogPostById(Long id, BlogPostDTO blogDTO) {
        Optional<BlogPost> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            BlogPost update = blog.get();
            if (!StringUtils.isEmpty(blogDTO.getTitle())) {
                update.setTitle(blogDTO.getTitle());
            }
            if (!StringUtils.isEmpty(blogDTO.getAuthor())) {
                update.setAuthor(blogDTO.getAuthor());
            }
            if (!StringUtils.isEmpty(blogDTO.getContent())) {
                update.setContent(blogDTO.getContent());
            }
            update.setUpdatedAt(new Date());
            blogRepository.save(update);
            return ResponseEntity.status(HttpStatus.OK).body(update);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found !!");
        }
    }

    @Override
    public ResponseEntity<?> getAllBlogPost(String author, String title, Pageable pageable) {
        Page<BlogPost> blogPage;
        if (!StringUtils.isEmpty(author) && !StringUtils.isEmpty(title)) {
            blogPage = blogRepository.findByAuthorAndTitle(author, title, pageable);
        } else if (!StringUtils.isEmpty(author)) {
            blogPage = blogRepository.findByAuthor(author, pageable);
        } else if (!StringUtils.isEmpty(title)) {
            blogPage = blogRepository.findByTitle(title, pageable);
        } else {
            blogPage = blogRepository.findAll(pageable);
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogPage);
    }

}
