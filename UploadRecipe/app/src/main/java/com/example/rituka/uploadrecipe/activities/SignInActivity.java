package com.example.rituka.uploadrecipe.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.api.RailsAPI;
import com.example.rituka.uploadrecipe.models.RequestData;
import com.example.rituka.uploadrecipe.models.ResultData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    EditText email , password;
    Button signin;
    TextView createuser , forgotpaswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        getSupportActionBar().hide();


        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.signin);
        forgotpaswd = (TextView)findViewById(R.id.forgotpaswd);
        createuser = (TextView)findViewById(R.id.createuser);



        createuser.setOnClickListener(this);
        forgotpaswd.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.signin) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RailsAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RailsAPI api = retrofit.create(RailsAPI.class);


            final RequestData requestData = new RequestData();
            requestData.setEmail(email.getText().toString());
            requestData.setPassword(password.getText().toString());

            Call<ResultData> result = api.getresult(requestData);

            result.enqueue(new Callback<ResultData>() {
                @Override
                public void onResponse(Call<ResultData> call, Response<ResultData> response) {

                    ResultData resultData = response.body();

                    if (resultData.isvalid()) {


                        final String PREF_NAME = "com.data.wfi.userdetails";
                        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorffordata = sharedPreferences.edit();

                        editorffordata.putString("AUTH_TOKEN",resultData.getAuth_token());
                        editorffordata.putString("EMAIL_ID",resultData.getEmail());
                        editorffordata.putString("NAME",resultData.getName());
                        editorffordata.apply();



                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (resultData.getMessage().equals("User Not Present")) {

                        Toast.makeText(getApplicationContext(), "User Not Present . Please Sign Up", Toast.LENGTH_LONG).show();
                        password.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<ResultData> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                    password.setText("");

                }
            });
        }else if (view.getId() == R.id.createuser){
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);

            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getBaseContext(), ForgotPasswordActivity.class);

            startActivity(intent);
        }

    }
}
