package com.example.rituka.uploadrecipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rituka.uploadrecipe.R;
import com.example.rituka.uploadrecipe.activities.WebPageCall;
import com.example.rituka.uploadrecipe.models.CommentsClass;
import com.example.rituka.uploadrecipe.models.PostClass;
import com.example.rituka.uploadrecipe.models.SearchResultClass;
import com.example.rituka.uploadrecipe.models.Shared_Preferences;

import java.util.ArrayList;

/**
 * Created by rituka on 27/10/17.
 */

public class AdvisesAdapter extends RecyclerView.Adapter<AdvisesAdapter.AdapterViewHolder>{
    Context context;
    ArrayList<CommentsClass> advisesAdapter;
    String username;

    public AdvisesAdapter(Context context, ArrayList<CommentsClass> advisesAdapter, String username) {
        this.context = context;
        this.advisesAdapter = advisesAdapter;
        this.username=username;
    }

    @Override
    public AdvisesAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.cardview_advises, parent, false);

        return new AdvisesAdapter.AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdvisesAdapter.AdapterViewHolder holder, int position) {
        holder.tvDishName.setText(advisesAdapter.get(position).getDishName());
        holder.tvusername.setText(username);
        holder.tvviewcomment.setText(advisesAdapter.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return advisesAdapter.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDishName, tvusername, tvviewcomment;
        CardView cardView;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            tvusername = itemView.findViewById(R.id.tvusername);
            tvviewcomment = itemView.findViewById(R.id.tvviewcomment);


            cardView=itemView.findViewById(R.id.cardview_advies);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            String idlink=advisesAdapter.get(position).getid();
            String dish_name=advisesAdapter.get(position).getDishName().toUpperCase();
            String url="http://192.168.43.182:3000/post?id="+idlink+"&dish="+dish_name+"&kiski="+username;
            Intent intent=new Intent(cardView.getContext(), WebPageCall.class);
            System.out.println(url);
            intent.putExtra("url",url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cardView.getContext().startActivity(new Intent(intent));
        }
    }
}