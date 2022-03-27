package com.example.foodapp_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.FieldPosition;

public class Food_Create_Edit extends AppCompatActivity {
    private EditText edtName,edtDescription,edtPrice ;
    private Spinner spinner;
    private Button btnEdit, btnCancel;
    private Food f;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_food);
        anhXa();
        CatchEventSpinner();
        getInforFood();
        addInformationFood();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void anhXa(){
        spinner = findViewById(R.id.spinner);
        edtName = findViewById(R.id.edt_name);
        edtDescription = findViewById(R.id.edt_des);
        edtPrice = findViewById(R.id.edt_price);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);
    }
    public void addInformationFood(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String des = edtDescription.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());
                int rating = Integer.parseInt(spinner.getSelectedItem().toString());
                Bundle bundle = new Bundle();
                Intent intent = new Intent(Food_Create_Edit.this,MainActivity.class);
                bundle.putString("name",name);
                bundle.putString("des",des);
                bundle.putDouble("price",price);
                bundle.putInt("rating",rating);
                intent.putExtras(bundle);
                setResult(201,intent);
                finish();
            }
        });
    }
    public void getInforFood(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle == null){
            return;
        }
            f = (Food) bundle.get("information");
            btnEdit.setText("Edit");
            edtName.setText(f.getFoodName());
            edtDescription.setText(f.getFoodDescription());
            edtPrice.setText(f.getFoodPrice() +"");

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = edtName.getText().toString();
                    String des = edtDescription.getText().toString();
                    Double price = Double.parseDouble(edtPrice.getText().toString());
                    int rating = Integer.parseInt(spinner.getSelectedItem().toString());
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(Food_Create_Edit.this,MainActivity.class);
                    bundle.putInt("id",f.getId());
                    bundle.putString("name",name);
                    bundle.putString("des",des);
                    bundle.putDouble("price",price);
                    bundle.putInt("rating",rating);
                    intent.putExtras(bundle);
                    setResult(200,intent);
                    finish();
                }
            });
    }
    public void CatchEventSpinner(){
        Integer[] soluong = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
}
