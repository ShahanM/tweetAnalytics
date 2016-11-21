package com.ishahanm.twitter;

import twitter4j.User;

import java.util.List;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public class Tweet {

    private String content;
    private User user;
    private Long tweetId;
    private List<String> tags;


    public Tweet(String content, User user, Long tweetId){
        this.content = content;
        this.user = user;
        this.tweetId = tweetId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
