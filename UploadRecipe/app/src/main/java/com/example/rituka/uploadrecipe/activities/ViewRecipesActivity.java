package com.example.rituka.uploadrecipe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.adapters.RecipesAdapter;
import com.example.rituka.uploadrecipe.models.PostClass;

import java.util.ArrayList;

public class ViewRecipesActivity extends AppCompatActivity {
    RecyclerView rvRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);
        rvRecipes=(RecyclerView)findViewById(R.id.rvRecipes);
        String username=getIntent().getStringExtra("username");
        ArrayList<PostClass> postClasses=(ArrayList<PostClass>) getIntent().getSerializableExtra("posts");
        RecipesAdapter recipesAdapter=new RecipesAdapter(getApplicationContext(),postClasses,username);
        rvRecipes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvRecipes.setAdapter(recipesAdapter);
        Toast.makeText(getApplicationContext(),"dsf",Toast.LENGTH_SHORT).show();
    }
}
