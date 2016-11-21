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

        List<Float> space = Lists.newArrayList();
        for(String text : textList){
            space.addAll(distinctTerms.stream().map(term -> determineTFIDF(text, term)).collect(Collectors.toList()));
            vectorSpace.add(new TextVector(text, space));
        }

        return vectorSpace;
    }

    private Float determineTFIDF(String text, String term){
        Float tf = calculateTermFrequency(text, term);
        Float idf = calculateInverseTexttFrequency(term);
        return tf*idf;
    }

    private Float calculateTermFrequency(String text, String term){
        List<String> tokens = Lists.newArrayList(regex.split(text));
        long count = tokens.stream()
                .filter(t -> t.toUpperCase().equals(term.toUpperCase())).count();
        return (float)count / (float)tokens.size();
    }

    private Float calculateInverseTexttFrequency(String term){
        long count = textList.stream().filter(text -> Lists.newArrayList(text.split(" ")).contains(term)).count();
        return (float)Math.log((float)textList.size() / (float)count);
    }

}
