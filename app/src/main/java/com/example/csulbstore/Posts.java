package com.example.csulbstore;

/**
 * Created by cmendoza on 11/13/15.
 */
public class Posts {

//    private String author;
    private String title;
    private String description;
    private String image;
    private String postAuthor;
    private String postTimestamp;
    private String text;
//    private String timestamp;

    public Posts() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public Posts(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public void setNewCond(String postDescription)
    {
        this.description = postDescription;
    }
//    public String getAuthor() { return author;}
    public String getTitle() { return title;}
    public String getDescription() { return description; }
//    public String getAuthor() { return postDescription;}
//    public String getName() { return name;}
//    public String getText() { return text;}
//    public String getTimestamp(){ return timestamp; }

}
