package com.example.rituka.uploadrecipe.api;

import com.example.rituka.uploadrecipe.models.FestivalModel;
import com.example.rituka.uploadrecipe.models.RequestData;
import com.example.rituka.uploadrecipe.models.ResultData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by rituka on 27/10/17.
 */

public interface RailsAPI {

    // Harshit Lappy Relative Ip

    String BASE_URL = "http://192.168.43.154:3000/api/v1/";



    @POST("signin")
    Call<ResultData> getresult(@Body RequestData requestData);

    @POST("signup")
    Call<ResultData> getresultsign(@Body RequestData requestData);

    @POST("resetpswd")
    Call<ResultData> getresetresult(@Body RequestData requestData);




       @GET("getallfestivals")
       Call<ArrayList<FestivalModel>> getallfestivals();
}
