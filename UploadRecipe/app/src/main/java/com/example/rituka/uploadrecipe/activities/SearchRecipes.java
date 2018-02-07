package com.example.rituka.uploadrecipe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.adapters.SearchAdapter;
import com.example.rituka.uploadrecipe.api.API;
import com.example.rituka.uploadrecipe.api.API_Interface;
import com.example.rituka.uploadrecipe.models.SearchResultClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRecipes extends AppCompatActivity implements View.OnClickListener{

    RecyclerView rvSearchResults;
    EditText etsearchRecipe;
    TextView tvnotfound;
    ImageView btnSearch;

    public static final String TAG="fga";

    ArrayList<SearchResultClass> searchResults=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipes);

        //Setting Ids
        rvSearchResults=(RecyclerView)findViewById(R.id.rvSearchResults);
        etsearchRecipe=(EditText)findViewById(R.id.etsearchRecipe);
        btnSearch=(ImageView) findViewById(R.id.btnSearch);
        tvnotfound=(TextView)findViewById(R.id.tvnotfound);


        rvSearchResults.setVisibility(View.GONE);

        btnSearch.setOnClickListener(this);


    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            case R.id.btnSearch:
                API_Interface searchAPI = API.getApiInstance().getApiInterface();
                searchAPI.getSearchResult(etsearchRecipe.getText().toString()).
                        enqueue(new Callback<ArrayList<SearchResultClass>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchResultClass>> call,
                                           Response<ArrayList<SearchResultClass>> response) {

                        if(response.body()!=null)
                        if(!(response.body().get(0).getId().equals("0"))) {

                            searchResults=new ArrayList<SearchResultClass>();

                            tvnotfound.setVisibility(view.GONE);
                            rvSearchResults.setVisibility(view.VISIBLE);
                            for (int i = 0; i < response.body().size(); i++) {
                                searchResults.add(i, response.body().get(i));
                            }
                            Toast.makeText(getApplicationContext(), "search"+searchResults.size(), Toast.LENGTH_SHORT).show();

                            SearchAdapter searchAdapter = new SearchAdapter(
                                    getApplicationContext(), searchResults
                            );
                            rvSearchResults.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rvSearchResults.setAdapter(searchAdapter);

                        }
                        else{
                            tvnotfound.setText("Search result \""+
                                    etsearchRecipe.getText().toString()+"\" not found");
                            tvnotfound.setVisibility(view.VISIBLE);
                            rvSearchResults.setVisibility(view.GONE);
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<SearchResultClass>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"ERROR!!",Toast.LENGTH_LONG).show();
                    }
                });


        }

    }
}
