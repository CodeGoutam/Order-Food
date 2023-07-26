package com.example.foodorder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorder.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    final databaseHelper helper = new databaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("imgView", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");

            binding.orderImg.setImageResource(image);
            binding.orderName.setText(name + "");
            binding.orderCost.setText(price + "");
            binding.description.setText(description + "");


            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (binding.customerName.getText().toString().length() != 0 && binding.customerPhone.getText().toString().length() != 0) {

                        boolean isInserted = helper.insertOrder(binding.customerName.getText().toString(),
                                binding.customerPhone.getText().toString(),
                                price,
                                image,
                                description,
                                name,
                                Integer.parseInt(binding.quantity.getText().toString()));
                        if (isInserted)
                            Toast.makeText(DetailActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DetailActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
//            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            binding.orderImg.setImageResource(cursor.getInt(4));
            binding.orderName.setText(cursor.getString(6));
            binding.orderCost.setText(String.format("%d", cursor.getInt(3)));
            binding.description.setText(cursor.getString(5));
            binding.customerName.setText(cursor.getString(1));
            binding.customerPhone.setText(cursor.getString(2));
            binding.orderBtn.setText("Update Order");
            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.customerName.getText().toString().length() != 0 && binding.customerPhone.getText().toString().length() != 0) {
                        boolean isUpdated = helper.updateOrder(binding.customerName.getText().toString(),
                                binding.customerPhone.getText().toString(),
                                Integer.parseInt(binding.orderCost.getText().toString()),
                                cursor.getInt(4),
                                binding.description.getText().toString(),
                                binding.orderName.getText().toString(),
                                Integer.parseInt(binding.quantity.getText().toString()),
                                id);
                        if (isUpdated) {
                            Toast.makeText(DetailActivity.this, " Order Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(DetailActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        final int[] quant = {1};
        binding.quantity.setText(String.valueOf(quant[0]));

        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quant[0] > 0) {
                    quant[0] = quant[0] - 1;
                    binding.quantity.setText(String.valueOf(quant[0]));
                }

            }
        });

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quant[0] = quant[0] + 1;
                binding.quantity.setText(String.valueOf(quant[0]));


            }
        });

    }


}