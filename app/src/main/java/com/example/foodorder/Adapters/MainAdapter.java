package com.example.foodorder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DetailActivity;
import com.example.foodorder.Models.MainModel;
import com.example.foodorder.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.imgView.setImageResource(list.get(position).getImage());
        holder.price.setText(list.get(position).getPrice());
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());

//when user click on any item the screen will switch to detail activity with the same food,price,image,description
        final MainModel model = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("imgView", model.getImage());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("name", model.getName());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("type",1);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView name, price, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            name = itemView.findViewById(R.id.foodName);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);


        }
    }

}
