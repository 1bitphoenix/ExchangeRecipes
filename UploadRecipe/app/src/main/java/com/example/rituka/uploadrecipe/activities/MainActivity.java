package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.activities.SearchRecipes;
import com.example.rituka.uploadrecipe.activities.UploadRecipe;
import com.example.rituka.uploadrecipe.api.API;
import com.example.rituka.uploadrecipe.api.API_Interface;
import com.example.rituka.uploadrecipe.api.RailsAPI;
import com.example.rituka.uploadrecipe.models.FestivalModel;
import com.example.rituka.uploadrecipe.models.ProfileClass;
import com.example.rituka.uploadrecipe.models.Shared_Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG="A:";

    Button btnprofile,btnfestivals, btnUpload,btnSearchRecipe, btnViewAprofile,btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting IDs
        btnUpload=(Button) findViewById(R.id.btnUpload);
        btnSearchRecipe=(Button)findViewById(R.id.btnSearchRecipe);
        btnfestivals=(Button)findViewById(R.id.btnfestivals);
        btnprofile=(Button)findViewById(R.id.btnprofile);
        btnViewAprofile=(Button)findViewById(R.id.btnViewAprofile);
        btnOrder=(Button)findViewById(R.id.btnOrder);

        //On click
        btnUpload.setOnClickListener(this);
        btnSearchRecipe.setOnClickListener(this);
        btnfestivals.setOnClickListener(this);
        btnprofile.setOnClickListener(this);
        btnViewAprofile.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpload://complete
                startActivity(new Intent(getBaseContext(),UploadRecipe.class));
                break;
            case R.id.btnSearchRecipe://top recipies
                startActivity(new Intent(getBaseContext(), SearchRecipes.class));
                break;
            case R.id.btnfestivals://adapter onlick

                    festivalsfunction();

                break;
            case R.id.btnprofile://complete
                Intent intent=new Intent(getBaseContext(),ProfileActivity.class);
                intent.putExtra("username",Shared_Preferences.getusername(view.getContext()));
                startActivity(intent);
                break;
            case R.id.btnViewAprofile:
                startActivity(new Intent(getBaseContext(),Search_a_profileActivity.class));
                break;
            case R.id.btnOrder:

                menuforcart();




                break;

        }
    }

    private void menuforcart() {


        startActivity(new Intent(getBaseContext(),ShopMenuActivity.class));



    }


    private void festivalsfunction() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RailsAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RailsAPI api = retrofit.create(RailsAPI.class);



        Call<ArrayList<FestivalModel>> result = api.getallfestivals();

        result.enqueue(new Callback<ArrayList<FestivalModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FestivalModel>> call, Response<ArrayList<FestivalModel>> response) {
                if (response.body() == null)
                {
                    return;
                }
                else
                {
                    ArrayList<FestivalModel> festival_list = response.body();


                    Intent intent = new Intent(getBaseContext() , FestivalsActivity.class);
                    intent.putExtra("festival_list", festival_list);
                    startActivity(intent);



                }
            }

            @Override
            public void onFailure(Call<ArrayList<FestivalModel>> call, Throwable t) {


                Toast.makeText(getApplicationContext() , "Network Error" , Toast.LENGTH_SHORT).show();

            }
        });


        return;

    }
}
