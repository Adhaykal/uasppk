package com.polstat.uasppk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_class);

        CardView exit = findViewById(R.id.cardFCBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindClassActivity.this, HomeActivity.class));
            }
        });

        CardView ks1 = findViewById(R.id.cardFC2KS1);
        ks1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindClassActivity.this, ClassDetailsActivity.class);
                it.putExtra("title", "Student of 2KS1");
                startActivity(it);
            }
        });

        CardView ks2 = findViewById(R.id.cardFC2KS2);
        ks2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindClassActivity.this, ClassDetailsActivity.class);
                it.putExtra("title", "Student of 2KS2");
                startActivity(it);
            }
        });

        CardView ks3 = findViewById(R.id.cardFC2KS3);
        ks3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindClassActivity.this, ClassDetailsActivity.class);
                it.putExtra("title", "Student of 2KS3");
                startActivity(it);
            }
        });

        CardView ks4 = findViewById(R.id.cardFC2KS4);
        ks4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindClassActivity.this, ClassDetailsActivity.class);
                it.putExtra("title", "Student of 2KS4");
                startActivity(it);
            }
        });

        CardView ks5 = findViewById(R.id.cardFC2KS5);
        ks5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindClassActivity.this, ClassDetailsActivity.class);
                it.putExtra("title", "Student of 2KS5");
                startActivity(it);
            }
        });
    }
}