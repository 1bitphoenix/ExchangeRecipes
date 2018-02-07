package com.example.rituka.uploadrecipe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.adapters.AdvisesAdapter;
import com.example.rituka.uploadrecipe.models.CommentsClass;
import com.example.rituka.uploadrecipe.models.PostClass;

import java.util.ArrayList;

public class ViewAdvisesActivity extends AppCompatActivity {

    RecyclerView rvAdvises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_advises);
        String username=getIntent().getStringExtra("username");
        ArrayList<CommentsClass> advises=(ArrayList<CommentsClass>) getIntent().getSerializableExtra("advises");
        rvAdvises=(RecyclerView)findViewById(R.id.rvAdvises);
        AdvisesAdapter advisesAdapter=new AdvisesAdapter(getApplicationContext(),advises,username);
        rvAdvises.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvAdvises.setAdapter(advisesAdapter);
    }
}
