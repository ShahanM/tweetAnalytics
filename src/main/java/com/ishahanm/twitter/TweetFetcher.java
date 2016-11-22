package com.ishahanm.twitter;

import com.google.common.collect.Lists;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public class TweetFetcher {

    private Twitter getInstance() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("twitter.properties");

        if (inputStream != null) {
            properties.load(inputStream);
        } else throw new FileNotFoundException("Could not load Property File");

        String oAuthConsumerKey = properties.getProperty("oAuthConsumerKey");
        String oAuthConsumerSecret = properties.getProperty("oAuthConsumerSecret");
        String oAuthAccessToken = properties.getProperty("oAuthAccessToken");
        String oAuthAccessTokenSecret = properties.getProperty("oAuthAccessTokenSecret");

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
            .setOAuthConsumerKey(oAuthConsumerKey)
            .setOAuthConsumerSecret(oAuthConsumerSecret)
            .setOAuthAccessToken(oAuthAccessToken)
            .setOAuthAccessTokenSecret(oAuthAccessTokenSecret);

        return new TwitterFactory(configurationBuilder.build()).getInstance();
    }

    public List<Tweet> getTweets() throws IOException, TwitterException {

        Twitter twitter = getInstance();

        Query query = new Query("#guitar");
        int numberOfTweets = 10;
        long lastID = Long.MAX_VALUE;
        ArrayList<Status> tweets = Lists.newArrayList();
        while (tweets.size() < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100) {
                query.setCount(100);
            } else {
                query.setCount(numberOfTweets - tweets.size());
            }
            QueryResult result = twitter.search(query);
            tweets.addAll(result.getTweets());
            for (Status tweet : tweets) {
                if (tweet.getId() < lastID) {
                    lastID = tweet.getId();
                }
            }
            query.setMaxId(lastID - 1);
        }
        return tweets.stream().map(tweet -> new Tweet(tweet.getText(), tweet.getUser(), tweet.getId())).collect(Collectors.toList());
    }


}
