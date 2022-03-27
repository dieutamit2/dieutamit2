package com.example.foodapp_midterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FoodViewHolder>{
    private ArrayList<Food> arrFood;
    private Context mContext;
    private IClickFoofListener iClickFoofListener;
    public RecyclerViewAdapter(ArrayList<Food> arrFood, Context mContext,IClickFoofListener iClickFoofListener) {
        this.arrFood = arrFood;
        this.mContext = mContext;
        this.iClickFoofListener = iClickFoofListener;
    }

    @NonNull
    @Override

    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_food,parent,false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        return foodViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        int id = holder.getAdapterPosition();
        Food f = arrFood.get(position);
        holder.txtFoodname.setText(f.getFoodName());
        holder.imgView.setImageResource(R.drawable.hambeger);
        holder.txtFoodDescription.setText(f.getFoodDescription());
        holder.txtPrice.setText(f.getFoodPrice()+"");
        holder.ratingBar.setRating(f.getRatingbar());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               iClickFoofListener.onClickDelete(f);
            }
        });
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickFoofListener.onClickItem(f);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrFood.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        private TextView txtFoodname,txtFoodDescription,txtPrice;
        private RatingBar ratingBar ;
        private ImageButton img_edit ,img_delete;
        private ImageView imgView;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_srcImage);
            txtFoodname = itemView.findViewById(R.id.txt_foodname);
            txtFoodDescription = itemView.findViewById(R.id.txt_fooddescription);
            txtPrice = itemView.findViewById(R.id.food_price);
            ratingBar = itemView.findViewById(R.id.food_ratingbar);
            img_edit = itemView.findViewById(R.id.btn_food_edit);
            img_delete = itemView.findViewById(R.id.btn_food_delete);
        }
    }
}
