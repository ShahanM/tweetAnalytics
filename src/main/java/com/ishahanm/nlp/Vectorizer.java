package com.ishahanm.nlp;

import com.google.common.collect.Lists;
import com.ishahanm.twitter.Tweet;
import com.ishahanm.twitter.TweetCollection;
import com.ishahanm.cluster.TextVector;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by shahanm on 11/18/16.
 *
 */
public class Vectorizer {

    private List<String> textList;
    private static final Pattern regex = Pattern.compile("([ \\t{}()\".:;\n])");

    public List<TextVector> processTweetCollection(TweetCollection collection){
        //TODO decouple this
        textList = collection.getTweets().stream().map(Tweet::getContent).collect(Collectors.toList());

        Set<String> distinctTerms = Tokenizer.tokenize(textList);

        List<TextVector> vectorSpace = Lists.newArrayList();

        List<Double> space = Lists.newArrayList();
        for(String text : textList){
            space.addAll(distinctTerms.stream().map(term -> determineTFIDF(text, term)).collect(Collectors.toList()));
            vectorSpace.add(new TextVector(text, space));
        }

        return vectorSpace;
    }

    private Double determineTFIDF(String text, String term){
        Double tf = calculateTermFrequency(text, term);
        Double idf = calculateInverseTexttFrequency(term);
        return tf*idf;
    }

    private Double calculateTermFrequency(String text, String term){
        List<String> tokens = Lists.newArrayList(regex.split(text));
        long count = tokens.stream()
                .filter(t -> t.toUpperCase().equals(term.toUpperCase())).count();
        return (double)count / (double)tokens.size();
    }

    private Double calculateInverseTexttFrequency(String term){
        long count = textList.stream().filter(text -> Lists.newArrayList(text.split(" ")).contains(term)).count();
        return Math.log(textList.size() / count);
    }

    private List<String> lemmatize(List<String> words){
        //TODO implement this
        return Lists.newArrayList();
    }

}
