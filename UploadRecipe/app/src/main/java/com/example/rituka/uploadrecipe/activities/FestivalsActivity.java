package com.example.rituka.uploadrecipe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.adapters.FestivalsAdapters;
import com.example.rituka.uploadrecipe.models.FestivalModel;

import java.util.ArrayList;

public class FestivalsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals);

        recyclerView=(RecyclerView)findViewById(R.id.rvFestivals);

        ArrayList<FestivalModel> myList = (ArrayList<FestivalModel>) getIntent().getSerializableExtra("festival_list");
        FestivalsAdapters festivalsAdapters=new FestivalsAdapters(getApplicationContext(),myList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(festivalsAdapters);





    }
}
