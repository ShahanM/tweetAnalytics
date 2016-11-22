package com.ishahanm.cluster;

import java.util.List;

/**
 * Created by shahanm on 11/18/16.
 *
 */
public class TextVector {

    public String content;
    public List<Double> vectorSpace;

    public TextVector(String content, List<Double> vectorSpace){
        this.content = content;
        this.vectorSpace = vectorSpace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Double> getVectorSpace() {
        return vectorSpace;
    }

    public void setVectorSpace(List<Double> vectorSpace) {
        this.vectorSpace = vectorSpace;
    }
}
