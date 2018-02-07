package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.api.API;
import com.example.rituka.uploadrecipe.api.API_Interface;
import com.example.rituka.uploadrecipe.models.CommentsClass;
import com.example.rituka.uploadrecipe.models.PostClass;
import com.example.rituka.uploadrecipe.models.ProfileClass;
import com.example.rituka.uploadrecipe.models.Shared_Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUsername;
    Button btnViewMyRecipes,btnViewMyAdvises;
    ArrayList<CommentsClass> advises = new ArrayList<CommentsClass>();
    ArrayList<PostClass> posts = new ArrayList<PostClass>();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //setting IDs
        btnViewMyAdvises=(Button)findViewById(R.id.btnViewMyComments);
        btnViewMyRecipes=(Button)findViewById(R.id.btnViewMyRecipes);
        tvUsername=(TextView)findViewById(R.id.tvUserName);

        username=getIntent().getStringExtra("username");
        btnViewMyAdvises.setVisibility(View.GONE);

        tvUsername.setText(username);

        btnViewMyAdvises.setOnClickListener(this);
        btnViewMyRecipes.setOnClickListener(this);

        API_Interface profileAPI= API.getApiInstance().getApiInterface();
        profileAPI.getprofile(username).enqueue(new Callback<ProfileClass>() {
            @Override
            public void onResponse(Call<ProfileClass> call, Response<ProfileClass> response) {
                if(response!=null) {
                    advises = response.body().getComments();
                    posts = response.body().getPosts();
                    Toast.makeText(getApplicationContext(),"Response",Toast.LENGTH_SHORT).show();

                }else return;
            }

            @Override
            public void onFailure(Call<ProfileClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Network Error!!",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.btnViewMyComments:
                Intent intent=new Intent(getBaseContext(),ViewAdvisesActivity.class);
                intent.putExtra("advises",advises);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.btnViewMyRecipes:

                Intent intent1=new Intent(getBaseContext(),ViewRecipesActivity.class);
                intent1.putExtra("posts",posts);
                intent1.putExtra("username", Shared_Preferences.getusername(view.getContext()));

                startActivity(intent1);
                break;
        }
    }
}
