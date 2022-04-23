package com.zemoso.springdemo.entity;

import com.zemoso.springdemo.dto.BlogDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Author name is required")
    @Column(name = "author_name")
    private String authorName;

    @NotNull(message = "Content is required")
    @Column(name = "content")
    private String content;

    public Blog() {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("Blog Instantiated");
    }

    public BlogDTO toDto(){
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlogId(this.id);
        blogDTO.setBlogTitle(this.title);
        blogDTO.setBlogContent(this.content);
        blogDTO.setBlogAuthorName(this.authorName);
        return blogDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
