package com.example.blog.blog;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Data
public class BlogPostDTO {
    @Valid
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotBlank(message = "Content cannot be null")
    private String content;
    @NotBlank(message = "Author cannot be null")
    private String Author;
   // @NotNull(message = "Date Created is required")
    private Date createdAt;
    private Date updatedAt;
}
