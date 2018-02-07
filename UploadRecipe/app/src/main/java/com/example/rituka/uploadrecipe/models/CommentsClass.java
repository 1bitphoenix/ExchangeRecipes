package com.example.rituka.uploadrecipe.models;

/**
 * Created by rituka on 27/10/17.
 */

public class CommentsClass implements java.io.Serializable {

    private String dishName;
    private String username;
    private String comment;

    private String id;

    public String getDishName()
    {
        return dishName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getComment()
    {
        return comment;
    }

    public String getid()
    {
        return id;
    }
}

