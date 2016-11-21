package com.ishahanm.twitter;

import java.util.List;

/**
 * Created by shahanm on 11/18/16.
 *
 */
public class TweetCollection {

    private List<Tweet> tweets;

    public TweetCollection(List<Tweet> tweets){
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
