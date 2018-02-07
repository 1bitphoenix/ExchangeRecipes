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
import com.example.rituka.uploadrecipe.activities.WebPageCall;
import com.example.rituka.uploadrecipe.models.FestivalModel;
import com.example.rituka.uploadrecipe.models.SearchResultClass;

import java.util.ArrayList;

/**
 * Created by rituka on 28/10/17.
 */

public class FestivalsAdapters extends RecyclerView.Adapter<FestivalsAdapters.FestivalViewHolder> {

    Context context;
    ArrayList<FestivalModel> festivalModels;

    public FestivalsAdapters(Context context, ArrayList<FestivalModel> festivalModels) {
        this.context = context;
        this.festivalModels = festivalModels;
    }

    @Override
    public FestivalsAdapters.FestivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.cardview_festivals, parent, false);

        return new FestivalsAdapters.FestivalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FestivalsAdapters.FestivalViewHolder holder, int position) {
        holder.tvplace.setText(festivalModels.get(position).getPlace());
        holder.tvname.setText(festivalModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return festivalModels.size();
    }

    public class FestivalViewHolder extends RecyclerView.ViewHolder {

        TextView tvplace,tvname;
//        CardView cardView;

        public FestivalViewHolder(View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvplace=itemView.findViewById(R.id.tvplace);


//            cardView = itemView.findViewById(R.id.mycardview);
//            cardView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {/*
//            int position=getAdapterPosition();
//            String idlink=searchResultObj.get(position).getId();
//            String dish_name=searchResultObj.get(position).getDish().toUpperCase();
//            String url="http://192.168.43.182:3000/post?id="+idlink+"&dish="+dish_name+"&kiski="+"arvindkalra";
//            Intent intent=new Intent(cardView.getContext(), WebPageCall.class);
//            System.out.println(url);
//            intent.putExtra("url",url);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            cardView.getContext().startActivity(new Intent(intent));*/
//        }
    }
}