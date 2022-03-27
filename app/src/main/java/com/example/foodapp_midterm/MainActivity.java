package com.example.foodapp_midterm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static DBConnect dbConnect;
    public static RecyclerViewAdapter recyclerViewAdapter ;
public static ArrayList<Food> foodArrayList;
private ImageButton btnAdd,btnCheck;
private RecyclerView recyclerView ;
private Toolbar toolbar;
private int flag;
private Button btncheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        dbConnect = new DBConnect(this,"FoodApp.sqlite",null,1);
        AnhXa();
        recyclerViewAdapter = new RecyclerViewAdapter(foodArrayList, MainActivity.this, new IClickFoofListener() {
            @Override
            public void onClickItem(Food f) {
                onClickGoToED(f);
            }

            @Override
            public void onClickDelete(Food f) {
                delete_database(f);
                foodArrayList.clear();
                recyclerViewAdapter.notifyDataSetChanged();
                displayData();
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        displayData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Insert.class);
                startActivityForResult(intent,101);
            }
        });

    }
    public void AnhXa(){
        recyclerView = findViewById(R.id.recycleview);
        btnAdd = findViewById(R.id.addFoodtoList);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_dehaze_24);
    }
    public void onClickGoToED(Food f){
        Intent intent = new Intent(MainActivity.this, Food_Edit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("information_to_update",f);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == 201){
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String des = bundle.getString("des");
            int rating = bundle.getInt("rating");
            Double price = bundle.getDouble("price");
            insert_into_database(name,des,rating,price);
            foodArrayList.clear();
            displayData();
        }
    }
    public void insert_into_database(String foodName, String foodDescription, int foodRating,Double foodPrice){
        String query_cratetable = "CREATE TABLE IF NOT EXISTS FoodApp(ID INTEGER PRIMARY KEY AUTOINCREMENT,FoodName VARCHAR(50), FoodDescription VARCHAR(200),FoodRating INT(3),FoodPrice Double)";
        String query_insertdata = "INSERT INTO FoodApp VALUES(null,'"+foodName+"','"+foodDescription+"','"+foodRating+"','"+foodPrice+"')";
        dbConnect.myQuery(query_cratetable);
        dbConnect.myQuery(query_insertdata);
    }
    public void delete_database(Food f){
        String query_delete = "Delete from FoodApp where ID = "+f.getId()+"";
        dbConnect.myQuery(query_delete);
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
    public static final void update_database(String dataFirst, String dataSecond,int dataThird,Double dataFourth, int id){
        String query_update = "UPDATE FoodApp SET FoodName = '"+dataFirst+"' , FoodDescription = '"+dataSecond+"' , FoodRating ='"+dataThird+"' ,FoodPrice = '"+dataFourth+"'  WHERE ID = "+id+"  ";
        dbConnect.myQuery(query_update);
    }
    public static void displayData(){
        String query = "SELECT * FROM FoodApp";
        Cursor cursor = dbConnect.getData(query);
        if(cursor == null){
            return;
        }else{
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodDescription = cursor.getString(2);
                int foodRating = cursor.getInt(3);
                Double foodPrice = cursor.getDouble(4);
                foodArrayList.add(new Food(id,foodName,foodDescription,foodRating,foodPrice));
            }
        }
    }
}