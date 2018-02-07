package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.api.API;
import com.example.rituka.uploadrecipe.api.API_Interface;
import com.example.rituka.uploadrecipe.models.CommentsClass;
import com.example.rituka.uploadrecipe.models.PostClass;
import com.example.rituka.uploadrecipe.models.ProfileClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_a_profileActivity extends AppCompatActivity {

    EditText etSearchProfile;
    ImageView ivSearchProfile;
    RecyclerView rvSearchProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_a_profile);
        rvSearchProfile=(RecyclerView)findViewById(R.id.rvSearchProfile);
        ivSearchProfile=(ImageView)findViewById(R.id.btnSearchProfile);
        etSearchProfile=(EditText)findViewById(R.id.etsearchProfile);
        ivSearchProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(),ProfileActivity.class);
                intent.putExtra("username",etSearchProfile.getText().toString());
                startActivity(intent);
            }
        });

    }
}
