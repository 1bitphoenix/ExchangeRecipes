package com.example.rituka.uploadrecipe.api;

import com.example.rituka.uploadrecipe.models.ArrayString;
import com.example.rituka.uploadrecipe.models.ID;
import com.example.rituka.uploadrecipe.models.PostRecipe;
import com.example.rituka.uploadrecipe.models.ProfileClass;
import com.example.rituka.uploadrecipe.models.RequestData;
import com.example.rituka.uploadrecipe.models.ResultData;
import com.example.rituka.uploadrecipe.models.SearchResultClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rituka on 24/10/17.
 */

public interface API_Interface {
    @GET("/add/regionList")
    Call<ArrayString> getRegions();

    @GET("/add/stateList?")
    Call<ArrayString> postRegionSelected(
            @Query("request") String regionSelected
    );

    @GET("/add/dishList?")
    Call<ArrayString> postStateSelected(
            @Query("request") String StateSelected
    );

    @POST("/add")
    Call<ID> postRecipe(
            @Body PostRecipe uploadRecipe
    );

    @POST("/add/other")
    Call<ID> postOtherRecipe(
      @Body PostRecipe uploadRecipe
    );

    @GET("/search")
    Call<ArrayList<SearchResultClass>> getSearchResult(
            @Query("dish") String dishname
    );

    @GET("getprofile?")
    Call<ProfileClass> getprofile(
            @Query("username") String username
    );



    @POST("/new/user")
    Call<ResultData> getresult(@Body RequestData requestData);


}
