package com.example.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SearchFoodByNameActivity extends AppCompatActivity {
        private SearchView sv;
        private Button btn;
        protected void onCreate(Bundle savedInstanceState)  {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.search_food_byname);

            sv = findViewById(R.id.searchname);
            btn = findViewById(R.id.startsearchbyname);
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String search = sv.getQuery().toString();
                    if(!search.isEmpty()){
                        Intent intent = new Intent(SearchFoodByNameActivity.this, FoundFoodActivity.class);
                        intent.putExtra("search", search);
                        startActivity(intent);
                    }
                }
            });

        }

}

