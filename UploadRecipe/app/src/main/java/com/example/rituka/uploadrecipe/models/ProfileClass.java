package com.example.rituka.uploadrecipe.models;

import java.util.ArrayList;

/**
 * Created by rituka on 27/10/17.
 */

public class ProfileClass {

    public ArrayList<CommentsClass> comments;

    private ArrayList<PostClass> posts;

    public ArrayList<CommentsClass> getComments() {
        return comments;
    }

    public ArrayList<PostClass> getPosts() {
        return posts;
    }

    public void setComments(ArrayList<CommentsClass> comments) {
        this.comments = comments;
    }

    public void setPosts(ArrayList<PostClass> posts) {
        this.posts = posts;
    }

}
