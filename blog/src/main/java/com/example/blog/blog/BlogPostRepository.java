package com.example.blog.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    Page<BlogPost> findByAuthorAndTitle(String author, String title, Pageable pageable);
    Page<BlogPost> findByAuthor(String author, Pageable pageable);
    Page<BlogPost> findByTitle(String title, Pageable pageable);


}
