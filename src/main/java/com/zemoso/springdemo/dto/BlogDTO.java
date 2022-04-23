package com.zemoso.springdemo.dto;


import com.zemoso.springdemo.entity.Blog;

public class BlogDTO {

    private int id;
    private String title;
    private String authorName;
    private String content;

    public Blog toEntity(){
        Blog blog = new Blog();
        blog.setId(this.id);
        blog.setTitle(this.title);
        blog.setAuthorName(this.authorName);
        blog.setContent(this.content);

        return blog;
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
