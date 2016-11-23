package com.ishahanm;

import com.google.common.collect.Lists;
import com.ishahanm.cluster.*;
import com.ishahanm.nlp.Vectorizer;
import com.ishahanm.stanfordnlp.SentimentAnalyzer;
import com.ishahanm.twitter.Tweet;
import com.ishahanm.twitter.TweetCollection;
import com.ishahanm.twitter.TweetFetcher;

import java.util.List;
import java.util.stream.Collectors;

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

        //startClustering(tweetCollection);
        analyzeSentiment(tweetCollection);
    }

    private static void analyzeSentiment(TweetCollection tweetCollection){
        List<String> tweetMsgs = Lists.newArrayList();
        tweetMsgs.addAll(tweetCollection.getTweets().stream().map(Tweet::getContent).collect(Collectors.toList()));
        try {
            SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
            for (String tweetMsg : tweetMsgs) {
                System.out.println(tweetMsg + " : " + sentimentAnalyzer.findSentiment(tweetMsg));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void startClustering(TweetCollection tweetCollection){
        System.out.println("Clustering");

        Vectorizer vectorizedList = new Vectorizer();

        List<TextVector> tweetVectors = vectorizedList.processTweetCollection(tweetCollection);
        List<Point> points = Lists.newArrayList();
        for(int i = 0; i < tweetVectors.size(); i++){
            for(int j = 0; j < tweetVectors.get(i).getVectorSpace().size(); j++) {
                points.add(new Point(i, tweetVectors.get(i).getVectorSpace().get(j)));
            }
        }
        KMeans kmeans = new KMeans(points);
        List<Cluster> kmeanResult = kmeans.execute();
        System.out.println("K-Mean results: " + kmeanResult.size());

        DBScan dbScan = new DBScan(points);
        List<Cluster> dbResults = dbScan.execute();
        System.out.println("DBScan clusters: " + dbResults.size());
    }
}
