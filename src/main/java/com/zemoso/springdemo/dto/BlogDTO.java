package com.zemoso.springdemo.dto;


import com.zemoso.springdemo.entity.Blog;

public class BlogDTO {

    private int blogId;
    private String blogTitle;
    private String blogAuthorName;
    private String blogContent;

    public Blog toEntity(){
        Blog blog = new Blog();
        blog.setId(this.blogId);
        blog.setTitle(this.blogTitle);
        blog.setAuthorName(this.blogAuthorName);
        blog.setContent(this.blogContent);

        return blog;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogAuthorName() {
        return blogAuthorName;
    }

    public void setBlogAuthorName(String blogAuthorName) {
        this.blogAuthorName = blogAuthorName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}
