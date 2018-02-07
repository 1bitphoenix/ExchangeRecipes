package com.example.rituka.uploadrecipe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.models.CartData;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<CartData> myList;
    TextView final_bill;
    Button order_final;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        myList = (ArrayList<CartData>) getIntent().getSerializableExtra("cart_data");
        order_final = (Button)findViewById(R.id.order_final);
        final_bill = (TextView)findViewById(R.id.final_bill);
        String bill="";
        int total = 0;

        for(int i = 0 ; i < myList.size() ; i++)
        {
            if (myList.get(i).getQuantity()!=0)
            {
                total = myList.get(i).getQuantity() + total;
                bill = bill +  myList.get(i).getDishname().toUpperCase() + "                    :                 Qty - " +  myList.get(i).getQuantity() +"\n";
            }
        }

        bill = bill + "Total = " + (total*100) + "\n";

        final_bill.setText(bill);





        order_final.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Order Has Been Placed" , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(),MainActivity.class));
    }
}
