package com.example.rituka.uploadrecipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.activities.WebPageCall;
import com.example.rituka.uploadrecipe.models.CommentsClass;
import com.example.rituka.uploadrecipe.models.PostClass;
import com.example.rituka.uploadrecipe.models.Shared_Preferences;

import java.util.ArrayList;

/**
 * Created by rituka on 28/10/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter <RecipesAdapter.RecipesViewHolder> {
    Context context;
    ArrayList<PostClass> recipeadapter;
    String username;

    public RecipesAdapter(Context context, ArrayList<PostClass> recipeadapter, String username) {
        this.context = context;
        this.recipeadapter = recipeadapter;
        this.username=username;
    }


    @Override
    public RecipesAdapter.RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.cardview_recipes, parent, false);

        return new RecipesAdapter.RecipesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipesViewHolder holder, int position) {
        holder.tvDish_Name.setText(recipeadapter.get(position).getdishname());
        holder.tvlikes.setText(recipeadapter.get(position).getLikes());

    }

    @Override
    public int getItemCount() {
        return recipeadapter.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDish_Name, tvlikes;
        CardView cardView;

        public RecipesViewHolder(View itemView) {
            super(itemView);
            tvDish_Name = itemView.findViewById(R.id.tvDish_Name);
            tvlikes = itemView.findViewById(R.id.tvlikes);

            cardView = itemView.findViewById(R.id.cardview_advies);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String idlink = recipeadapter.get(position).getId();
            String dish_name = recipeadapter.get(position).getdishname().toUpperCase();

            String url = "http://192.168.43.182:3000/post?id=" + idlink + "&dish=" + dish_name
                    + "&kiski=" + username;

            Intent intent = new Intent(cardView.getContext(), WebPageCall.class);
            System.out.println(url);
            intent.putExtra("url", url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cardView.getContext().startActivity(new Intent(intent));
        }
    }
}