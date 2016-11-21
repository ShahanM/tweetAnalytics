package com.ishahanm.nlp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public final class Tokenizer {

    private Tokenizer(){
        throw new RuntimeException();
    }

    private static final List<String> removeList =
        Lists.newArrayList("\"","\r","\n","(",")","[","]","{","}","","."," ",",");

    public static Set<String> tokenize(String text){
        Set<String> distinctTerms = Lists.newArrayList(text.split(" ")).stream()
            .filter(term -> !StopWordsHandler.isStopWord(term)).collect(Collectors.toSet());

        removeList.forEach(distinctTerms::remove);

        return  distinctTerms;
    }

    public static Set<String> tokenize(List<String> textList){
        Set<String> distinctTerms = Sets.newHashSet();
        for(String text : textList){
            distinctTerms.addAll(Tokenizer.tokenize(text));
        }
        return distinctTerms;
    }


}
