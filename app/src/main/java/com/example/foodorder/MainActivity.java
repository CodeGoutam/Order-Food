package com.example.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.foodorder.Adapters.MainAdapter;
import com.example.foodorder.Models.MainModel;
import com.example.foodorder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.bread, "100", "Bread", "veg"));
        list.add(new MainModel(R.drawable.burgur, "90", "Burgur", "veg"));
        list.add(new MainModel(R.drawable.cake, "80", "Cake", "veg"));
        list.add(new MainModel(R.drawable.dessert, "120", "Dessert", "veg"));
        list.add(new MainModel(R.drawable.icecream, "100", "Ice-Cream", "veg"));
        list.add(new MainModel(R.drawable.pasta, "200", "Pasta", "veg"));
        list.add(new MainModel(R.drawable.pizza, "270", "Pizza", "veg"));
        list.add(new MainModel(R.drawable.salad, "150", "Salad", "veg"));
        list.add(new MainModel(R.drawable.sanwich, "70", "Sandwich", "veg"));
        list.add(new MainModel(R.drawable.bread, "100", "Bread", "veg"));
        list.add(new MainModel(R.drawable.burgur, "90", "Burgur", "veg"));
        list.add(new MainModel(R.drawable.cake, "80", "Cake", "veg"));
        list.add(new MainModel(R.drawable.dessert, "120", "Dessert", "veg"));
        list.add(new MainModel(R.drawable.icecream, "100", "Ice-Cream", "veg"));
        list.add(new MainModel(R.drawable.pasta, "200", "Pasta", "veg"));
        list.add(new MainModel(R.drawable.pizza, "270", "Pizza", "veg"));
        list.add(new MainModel(R.drawable.salad, "150", "Salad", "veg"));
        list.add(new MainModel(R.drawable.sanwich, "70", "Sandwich", "veg"));
//        storiesRecyclerView.setAdapter(new storiesAdapterClass(storiesArrayList));

        binding.mainRecyclerView.setAdapter(new MainAdapter(list, this));

//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        binding.mainRecyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(this,2);
        binding.mainRecyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.order) {
            startActivity(new Intent(MainActivity.this, Orders_Activity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}