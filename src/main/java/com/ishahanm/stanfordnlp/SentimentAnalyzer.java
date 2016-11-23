package com.ishahanm.stanfordnlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by shahanm on 11/23/16.
 *
 */
public class SentimentAnalyzer {

    static StanfordCoreNLP pipeline;

    public SentimentAnalyzer() throws IOException{
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("stanfordnlp.properties");

        if (inputStream != null) {
            properties.load(inputStream);
        } else throw new FileNotFoundException("Could not load Property File");
        pipeline = new StanfordCoreNLP(properties);
    }

    public int findSentiment(String tweet){
        int mainSentiment = 0;
        if(tweet != null && tweet.length() > 0){
            int longest = 0;
            Annotation annotation = pipeline.process(tweet);
            for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)){
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if(partText.length() > longest){
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        return mainSentiment;
    }

}
