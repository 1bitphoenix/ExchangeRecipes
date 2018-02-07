package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    EditText email_signup , phone_no , password_password ;
    Button reset;
    TextView signin_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        password_password = (EditText)findViewById(R.id.password_password);

        signin_again = (TextView)findViewById(R.id.signin_again);

        phone_no = (EditText)findViewById(R.id.phone_no);
        email_signup = (EditText)findViewById(R.id.email_signup);

        reset = (Button)findViewById(R.id.reset);


        signin_again.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reset) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RailsAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RailsAPI api = retrofit.create(RailsAPI.class);


            RequestData requestData = new RequestData();
            requestData.setEmail(email_signup.getText().toString());
            requestData.setPassword(password_password.getText().toString());
            requestData.setPhone_no(phone_no.getText().toString());

            Call<ResultData> result = api.getresetresult(requestData);

            result.enqueue(new Callback<ResultData>() {
                @Override
                public void onResponse(Call<ResultData> call, Response<ResultData> response) {

                    ResultData resultData = response.body();
                    if (resultData.isvalid()) {


                        Toast.makeText(getApplicationContext(), "Password reset successful", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getBaseContext(), SignInActivity.class);

                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Details Do Not Match", Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Call<ResultData> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Intent intent = new Intent(getBaseContext(), SignInActivity.class);

            startActivity(intent);
        }
    }
}
