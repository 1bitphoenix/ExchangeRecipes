package com.example.rituka.uploadrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.models.CartData;

import java.util.ArrayList;

/**
 * Created by rituka on 28/10/17.
 */



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public static int items ;
    Context context;
    ArrayList<CartData> cartDatas;

    public CartAdapter(Context context, ArrayList<CartData> cartDatas) {
        this.context = context;
        this.cartDatas = cartDatas;
        items = 0 ;

    }


    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.cart_item_card, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        holder.cartDishName.setText(cartDatas.get(position).getDishname());
        holder.cart_state.setText( cartDatas.get(position).getState());
        holder.price_food.setText("Rs 100/-");


    }

    @Override
    public int getItemCount() {
        return cartDatas.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView price_food , cart_state , cartDishName ;
        ImageView add_item_to_cart;

        public CartViewHolder(View itemView) {
            super(itemView);
            price_food = itemView.findViewById(R.id.price_food);
            cart_state = itemView.findViewById(R.id.cart_state);
            cartDishName = itemView.findViewById(R.id.cartDishName);
            add_item_to_cart = itemView.findViewById(R.id.add_item_to_cart);

            add_item_to_cart.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            items++;
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
            cartDatas.get(position).setQuantity(cartDatas.get(position).getQuantity() + 1);







        }
    }
}

