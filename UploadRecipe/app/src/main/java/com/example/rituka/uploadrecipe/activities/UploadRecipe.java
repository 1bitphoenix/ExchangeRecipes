package com.example.rituka.uploadrecipe.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.fragments.Upload;

public class UploadRecipe extends AppCompatActivity {

    FragmentManager fragmentManager;
    Upload upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);
        fragmentManager=getSupportFragmentManager();
        upload=new Upload();

        fragmentManager
                .beginTransaction()
                .replace(R.id.flFragContainer, upload)
                .commit();
    }
}
