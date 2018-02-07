package com.example.rituka.uploadrecipe.models;

/**
 * Created by rituka on 25/10/17.
 */

public class PostRecipe {
    String username;
    String region;
    String state;
    String dish_name;
    String video;
    String description;

    public PostRecipe(String username, String region, String state, String dish_name, String video, String description) {
        this.username = username;
        this.region = region;
        this.state = state;
        this.dish_name = dish_name;
        this.video = video;
        this.description = description;
    }
}
