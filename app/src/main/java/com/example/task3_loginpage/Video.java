package com.example.task3_loginpage;

public class Video {
    private String videoUrl, title, desc, likes, comments, shares;

    public Video(String videoUrl, String title, String desc, String likes, String comments, String shares) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.desc = desc;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }
    public String getComments() {
        return comments;
    }
    public String getLikes() {
        return likes;
    }
    public String getShares() {
        return shares;
    }
}
