package com.example.foodorder.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DetailActivity;
import com.example.foodorder.Models.OrderListModel;
import com.example.foodorder.R;
import com.example.foodorder.databaseHelper;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.orderListViewHolder> {
    @NonNull
    ArrayList<OrderListModel> ordersArrayList;
    Context context;

    public OrderListAdapter(ArrayList<OrderListModel> ordersArrayList, Context context) {
        this.context = context;
        this.ordersArrayList = ordersArrayList;

    }


    @Override
    public orderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        return new orderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderListViewHolder holder, int position) {
        final OrderListModel model = ordersArrayList.get(position);
        holder.name.setText(ordersArrayList.get(position).getName());
        holder.price.setText(ordersArrayList.get(position).getPrice());
        holder.orderNumber.setText(ordersArrayList.get(position).getOrderNumber());
        holder.image.setImageResource(ordersArrayList.get(position).getImage());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setTitle("Are you sure to delete order?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        databaseHelper helper = new databaseHelper(context);
                        if (helper.deleteOrder(model.getOrderNumber()) > 0) {
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }).show();


                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);


            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                databaseHelper helper=new databaseHelper(context);
//if (helper.deleteOrder(model.getOrderNumber())>0){
//    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
//                }
//else{
//    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//}
//                return false;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }

    public class orderListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, orderNumber, price;

        public orderListViewHolder(@Nullable View view) {
            super(view);
            image = view.findViewById(R.id.imgView);
            name = view.findViewById(R.id.foodName);
            orderNumber = view.findViewById(R.id.OrderNumber);
            price = view.findViewById(R.id.price);


        }
    }


}
