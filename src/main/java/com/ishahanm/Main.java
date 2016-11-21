package com.ishahanm;

import com.google.common.collect.Lists;
import com.ishahanm.cluster.DBScan;
import com.ishahanm.cluster.Point;
import com.ishahanm.cluster.TextVector;
import com.ishahanm.nlp.Vectorizer;
import com.ishahanm.twitter.Tweet;
import com.ishahanm.twitter.TweetCollection;
import com.ishahanm.twitter.TweetFetcher;

import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        System.out.println("Okay we just started");
        TweetFetcher tweetFetcher = new TweetFetcher();
        List<Tweet> tweets = Lists.newArrayList();
        try{
            System.out.println("Fetching Tweets");
            tweets = tweetFetcher.getTweets();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Prepping for Clustering");
        TweetCollection tweetCollection = new TweetCollection(tweets);

        startClustering(tweetCollection);
    }

    private static void startClustering(TweetCollection tweetCollection){
        System.out.println("Clustering");

        Vectorizer vectorizedList = new Vectorizer();

        List<TextVector> tweetVectors = vectorizedList.processTweetCollection(tweetCollection);
        Vector<Point> points = new Vector<>();
        for(int i = 0; i < tweetVectors.size(); i++){
            for(int j = 0; j < tweetVectors.get(i).getVectorSpace().size(); j++) {
                points.add(new Point(i, tweetVectors.get(i).getVectorSpace().get(j)));
            }
        }

        DBScan dbScan = new DBScan(points);
        Vector<List> results = dbScan.execute();

        System.out.println(results.size());
    }
}
