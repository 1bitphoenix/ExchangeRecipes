package com.example.rituka.uploadrecipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.activities.SearchRecipes;
import com.example.rituka.uploadrecipe.activities.WebPageCall;
import com.example.rituka.uploadrecipe.models.SearchResultClass;

import java.util.ArrayList;

/**
 * Created by rituka on 26/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<SearchResultClass> searchResultObj;

    public SearchAdapter(Context context, ArrayList<SearchResultClass> searchResultObj) {
        this.context = context;
        this.searchResultObj = searchResultObj;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.searchlist_item_cards, parent, false);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.tvlistUserName.setText(searchResultObj.get(position).getUsername());
        holder.tvlistDishName.setText(searchResultObj.get(position).getDish());
        holder.tvNoOflikes.setText(searchResultObj.get(position).getLikes().toString());

    }

    @Override
    public int getItemCount() {
        return searchResultObj.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvlistDishName, tvlistUserName, tvNoOflikes;
        CardView cardView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            tvlistDishName = itemView.findViewById(R.id.tvlistDishName);
            tvlistUserName = itemView.findViewById(R.id.tvlistUserName);
            tvNoOflikes = itemView.findViewById(R.id.tvNoOflikes);


            cardView=itemView.findViewById(R.id.mycardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            String idlink=searchResultObj.get(position).getId();
            String dish_name=searchResultObj.get(position).getDish().toUpperCase();
            String url="http://192.168.43.182:3000/post?id="+idlink+"&dish="+dish_name+"&kiski="+"arvindkalra";
            Intent intent=new Intent(cardView.getContext(), WebPageCall.class);
            System.out.println(url);
            intent.putExtra("url",url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cardView.getContext().startActivity(new Intent(intent));
        }
    }
}