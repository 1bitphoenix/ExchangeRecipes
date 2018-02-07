package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.adapters.CartAdapter;
import com.example.rituka.uploadrecipe.models.CartData;

import java.util.ArrayList;

public class ShopMenuActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView cart_view;

    ArrayList<CartData> cartdata;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);

        checkout = (Button)findViewById(R.id.checkout);
        checkout.setOnClickListener(this);

        cartdata = new ArrayList<>();
        for(int i  = 0 ;  i < 10 ; i++ )
        {
            cartdata.add(new CartData("Dosa","Roorkee",i));
        }



        cart_view=(RecyclerView)findViewById(R.id.cart_view);

        CartAdapter cartAdapter = new CartAdapter(
                getApplicationContext(), cartdata
        );


        cart_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cart_view.setAdapter(cartAdapter);


    }

    @Override
    public void onClick(View view) {

        if (CartAdapter.items == 0 )
        {
            Toast.makeText(this, "No Item In The Cart", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this , CheckoutActivity.class);
            intent.putExtra("cart_data", cartdata);
            startActivity(intent);
        }



    }
}
