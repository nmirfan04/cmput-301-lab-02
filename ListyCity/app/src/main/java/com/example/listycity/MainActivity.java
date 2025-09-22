package com.example.listycity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCity, deleteCity, confirmCity;
    EditText inputCity;
    LinearLayout showText;
    int selectedPosition= -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.delete_city);
        confirmCity = findViewById(R.id.confirm_city);
        inputCity = findViewById(R.id.enter_city);
        showText = findViewById(R.id.text_input);

        /* The idea of using setVisibility method is from OpenAI, ChatGPT,
        "i want the list to show up only when an add button in the app is clicked and go away afterwards.
         how can i do that", 2025-09-09 */
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showText.setVisibility(View.VISIBLE);
            }
        });

        // The idea of using OnItemClickListener is from OpenAI, ChatGPT,
        // "i tried using setonclicklistener to get an item from a list displayed in the app but i cannot access individual items.
        //  what can i do to access the item ", 2025-09-09
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition !=-1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                }
            }
        });
        
        /* Author: Ahmet Tanakol https://stackoverflow.com/users/1382672/ahmet-tanakol
        Title: getting a string from a listview item in setOnItemClickListener
        Date: 2027-07-12
        License: License: CC-BY-SA 4.0 (International)
        Date Accessed: 2025-09-09
        URL: https://stackoverflow.com/questions/11683547/getting-a-string-from-a-listview-item-in-setonitemclicklistener
        */
        confirmCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputCity.getText().toString().strip();
                if (!inputText.isEmpty()) {
                    dataList.add(inputCity.getText().toString());
                    cityAdapter.notifyDataSetChanged();
                    inputCity.setText("");
                    showText.setVisibility(View.GONE);
                }
            }
        });
    }
}