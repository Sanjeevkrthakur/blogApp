package com.example.blog.blog;

import lombok.Data;

@Data
public class BlogPostFilterRequest {
    private String title;
    private String content;
    private String author;
}
