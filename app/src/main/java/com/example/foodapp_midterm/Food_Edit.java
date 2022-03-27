package com.example.foodapp_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Food_Edit extends AppCompatActivity {
     private EditText edt_updateName,edt_updateDes,edt_updatePrice;
     private Spinner spinnerUpdate;
     private Button btnUpdate,btnCancel_update;
     private Food f ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        AnhXa();
        getData();
        update();
    }
    public void AnhXa(){
        spinnerUpdate = findViewById(R.id.spinner_updateRating);
        edt_updateName = findViewById(R.id.edt_update_Name);
        edt_updateDes = findViewById(R.id.edt_update_Des);
        edt_updatePrice = findViewById(R.id.edt_update_Price);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel_update = findViewById(R.id.btnCancel_update);
        Integer[] soluong = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinnerUpdate.setAdapter(arrayAdapter);
    }
    public void getData(){
        Bundle  bundle = getIntent().getExtras();
        f = (Food) bundle.getSerializable("information_to_update");
        edt_updateName.setText(f.getFoodName());
        edt_updateDes.setText(f.getFoodDescription());
        edt_updatePrice.setText(f.getFoodPrice()+"");
    }
    public void update(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_updateName.getText().toString();
                String des = edt_updateDes.getText().toString();
                Double price = Double.parseDouble(edt_updatePrice.getText().toString());
                int rating = Integer.parseInt(spinnerUpdate.getSelectedItem().toString());
                MainActivity.update_database(name,des,rating,price,f.getId());
                MainActivity.foodArrayList.clear();
                MainActivity.recyclerViewAdapter.notifyDataSetChanged();
                MainActivity.displayData();
                finish();
            }
        });
        btnCancel_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}