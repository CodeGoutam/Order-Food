package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.opengl.GLDebugHelper;
import android.os.Bundle;

import com.example.foodorder.Adapters.OrderListAdapter;
import com.example.foodorder.Models.OrderListModel;
import com.example.foodorder.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class Orders_Activity extends AppCompatActivity {
ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper helper=new databaseHelper(this);
        ArrayList<OrderListModel> list=helper.getOrders();


binding.ordersRecyclerView.setAdapter(new OrderListAdapter(list,this));

        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.ordersRecyclerView.setLayoutManager(manager);


    }
}