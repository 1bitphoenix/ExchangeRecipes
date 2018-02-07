package com.example.rituka.uploadrecipe.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.api.API;
import com.example.rituka.uploadrecipe.api.API_Interface;
import com.example.rituka.uploadrecipe.models.ArrayString;
import com.example.rituka.uploadrecipe.models.ID;
import com.example.rituka.uploadrecipe.models.PostRecipe;
import com.example.rituka.uploadrecipe.models.Shared_Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Upload extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String region,state,dish, description,video;
    TextView tvRegion,tvState,tvDish;
    Spinner region_spinner,state_spinner,dish_spinner;
    EditText etDishName,etDishDiscription, etVideoLink;
    Button btnUploadRecipe;

    final ArrayList<String> regions=new ArrayList<String>();
    final ArrayList<String> states=new ArrayList<String>();
    final ArrayList<String> dishes=new ArrayList<String>();

    public Upload() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_upload,container,false);

        //item id
        tvRegion=rootview.findViewById(R.id.tvRegion);
        tvDish=rootview.findViewById(R.id.tvDish);
        tvState=rootview.findViewById(R.id.tvState);
        region_spinner=rootview.findViewById(R.id.region_spinner);
        state_spinner=rootview.findViewById(R.id.state_spinner);
        dish_spinner=rootview.findViewById(R.id.dish_spinner);
        etDishDiscription=rootview.findViewById(R.id.etDishDiscription);
        etDishName=rootview.findViewById(R.id.etDishName);
        etVideoLink=rootview.findViewById(R.id.etVideoLink);
        btnUploadRecipe=rootview.findViewById(R.id.btnUploadRecipe);

        //make invisible
        etVideoLink.setVisibility(rootview.GONE);
        etDishName.setVisibility(rootview.GONE);
        etDishDiscription.setVisibility(rootview.GONE);
        btnUploadRecipe.setVisibility(rootview.GONE);

        //Create Region List
        API_Interface regionApi= API.getApiInstance().getApiInterface();
        regions.add(0,"NONE");
        regionApi.getRegions().enqueue(new Callback<ArrayString>() {
            @Override
            public void onResponse(Call<ArrayString> call, Response<ArrayString> response) {

                for (int i=0;i<response.body().getList().size();i++) {
                    regions.add(i+1,response.body().getList().get(i));
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.
                        layout.simple_spinner_item,regions);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                region_spinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayString> call, Throwable t) {
                tvRegion.setText("Error!!");
            }
        });

        btnUploadRecipe.setOnClickListener(this);

        region_spinner.setOnItemSelectedListener(this);

        return rootview;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()){
            case R.id.region_spinner:

                if(parent.getItemAtPosition(pos).toString().equals("OTHER")){
                    //Make spinners invisible
                    tvDish.setVisibility(view.GONE);
                    tvState.setVisibility(view.GONE);
                    state_spinner.setVisibility(view.GONE);
                    dish_spinner.setVisibility(view.GONE);
                    //Make ets visible
                    etDishName.setVisibility(view.VISIBLE);
                    makeVisible(view);

                    tvRegion.setText("OTHER");
                }
                else if(!(parent.getItemAtPosition(pos).toString().equals("NONE"))) {
                    tvRegion.setText(parent.getItemAtPosition(pos).toString());

                    //make states option visible
                    tvState.setVisibility(view.VISIBLE);
                    state_spinner.setVisibility(view.VISIBLE);

                    API_Interface sendRegion = API.getApiInstance().getApiInterface();
                    state_spinner.setOnItemSelectedListener(this);
                    states.add(0,"NONE");
                    sendRegion.postRegionSelected(parent.getItemAtPosition(pos).toString())
                            .enqueue(new Callback<ArrayString>() {
                                @Override
                                public void onResponse(Call<ArrayString> call, Response<ArrayString> response) {
                                    for (int i = 0; i < response.body().getList().size(); i++) {
                                        states.add(i+1, response.body().getList().get(i));
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout
                                            .simple_spinner_item, states);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    state_spinner.setAdapter(adapter);

                                }

                                @Override
                                public void onFailure(Call<ArrayString> call, Throwable t) {
                                    tvState.setText("ERROR");
                                }

                            });
                }break;

            case R.id.state_spinner:

                if(parent.getItemAtPosition(pos).toString().equals("OTHER")){
                    //Make spinners invisible
                    tvDish.setVisibility(view.GONE);
                    dish_spinner.setVisibility(view.GONE);
                    //Make ets visible
                    etDishName.setVisibility(view.VISIBLE);
                    makeVisible(view);

                    tvState.setText("OTHER");
                }
                else if(!(parent.getItemAtPosition(pos).toString().equals("NONE"))) {
                    tvState.setText(parent.getItemAtPosition(pos).toString());
                    API_Interface sendState = API.getApiInstance().getApiInterface();

                    //make dishes options visible

                    tvDish.setVisibility(view.VISIBLE);
                    dish_spinner.setVisibility(view.VISIBLE);


                    dish_spinner.setOnItemSelectedListener(this);
                    dishes.add(0,"NONE");
                    sendState.postStateSelected(parent.getItemAtPosition(pos).toString())
                            .enqueue(new Callback<ArrayString>() {
                                @Override
                                public void onResponse(Call<ArrayString> call, Response<ArrayString> response) {
                                    for (int i = 0; i < response.body().getList().size(); i++) {
                                        dishes.add(i+1, response.body().getList().get(i));
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout
                                            .simple_spinner_item, dishes);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    dish_spinner.setAdapter(adapter);

                                }

                                @Override
                                public void onFailure(Call<ArrayString> call, Throwable t) {
                                    tvDish.setText("ERROR");
                                }

                            });
                }break;

            case R.id.dish_spinner:
                if(!(parent.getItemAtPosition(pos).toString().equals("NONE"))) {
                    tvDish.setText(parent.getItemAtPosition(pos).toString());
                    if (parent.getItemAtPosition(pos).toString().equals("OTHER")) {
                        etDishName.setVisibility(view.VISIBLE);
                    }
                    makeVisible(view);
                }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void makeVisible(View view){
        etDishDiscription.setVisibility(view.VISIBLE);
        etVideoLink.setVisibility(view.VISIBLE);
        btnUploadRecipe.setVisibility(view.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if(!(etDishDiscription.getText().toString().equals(""))) {
            region = tvRegion.getText().toString();
            state = tvState.getText().toString();
            if (etDishName.getText().toString().equals("")){
                dish = tvDish.getText().toString();
            } else dish = etDishName.getText().toString();
            description = etDishDiscription.getText().toString();

            video = etVideoLink.getText().toString();

            PostRecipe uploadRecipe = new PostRecipe(Shared_Preferences.getusername(view.getContext()), region, state, dish, video, description);
            API_Interface postrecipe = API.getApiInstance().getApiInterface();

            if (!(tvRegion.getText().toString().equals("OTHER") ||
                    tvState.getText().toString().equals("OTHER") ||
                    tvDish.getText().toString().equals("OTHER"))) {
                postrecipe.postRecipe(uploadRecipe).enqueue(new Callback<ID>() {
                    @Override
                    public void onResponse(Call<ID> call, Response<ID> response) {
                        Toast.makeText(getContext(), "Recipe Uploaded (:", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ID> call, Throwable t) {
                        Toast.makeText(getContext(), "Upload Failed ):", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                postrecipe.postOtherRecipe(uploadRecipe).enqueue(new Callback<ID>() {
                    @Override
                    public void onResponse(Call<ID> call, Response<ID> response) {
                        Toast.makeText(getContext(), "Recipe Uploaded (:", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ID> call, Throwable t) {
                        Toast.makeText(getContext(), "Upload Failed ):", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        else {
            Toast.makeText(getContext(),"Description Compulsory!!",Toast.LENGTH_LONG).show();
        }

    }
}
