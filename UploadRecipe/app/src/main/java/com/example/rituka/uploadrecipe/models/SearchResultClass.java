package com.example.rituka.uploadrecipe.models;

/**
 * Created by rituka on 26/10/17.
 */

public class SearchResultClass {
    String id,dish,username;
    Integer likes;

    public SearchResultClass(String id, String dish, String username, Integer likes) {
        this.id = id;
        this.dish = dish;
        this.username = username;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
