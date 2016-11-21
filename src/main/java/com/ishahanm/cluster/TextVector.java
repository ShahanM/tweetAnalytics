package com.ishahanm.cluster;

import java.util.List;

/**
 * Created by shahanm on 11/18/16.
 *
 */
public class TextVector {

    public String content;
    public List<Float> vectorSpace;

    public TextVector(String content, List<Float> vectorSpace){
        this.content = content;
        this.vectorSpace = vectorSpace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Float> getVectorSpace() {
        return vectorSpace;
    }

    public void setVectorSpace(List<Float> vectorSpace) {
        this.vectorSpace = vectorSpace;
    }
}
