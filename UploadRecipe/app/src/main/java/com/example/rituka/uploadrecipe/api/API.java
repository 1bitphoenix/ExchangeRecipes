package com.example.rituka.uploadrecipe.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rituka on 24/10/17.
 */

public class API {

    private static API apiInstance;

    private API_Interface apiInterface;

    public API_Interface getApiInterface(){
        return apiInterface;
    }

    private API() {
        Retrofit retrofit = new Retrofit.Builder()
                //https://wfi2017.herokuapp.com
                // http://192.168.43.182:3000

                // Arvind Lappy Relative Ip

                .baseUrl("http://192.168.43.182:3000")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();
        apiInterface=retrofit.create(API_Interface.class);
    }

    public static API getApiInstance(){
        if(apiInstance==null){
            apiInstance=new API();
        }
        return apiInstance;
    }
}
