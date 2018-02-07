package com.example.rituka.uploadrecipe.models;

/**
 * Created by rituka on 27/10/17.
 */

public class PostClass implements java.io.Serializable {

    private String dishname;
    private String id;
    private String likes;


    public String getdishname()
    {
        return dishname;
    }

    public String getId()
    {
        return id;
    }

    public String getLikes()
    {
        return likes;
    }
}
