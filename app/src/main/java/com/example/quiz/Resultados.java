package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        int tiempo = intent.getIntExtra("tiempo",0);
        TextView results = (TextView) findViewById(R.id.results);

        results.setText("Tu puntuación es:" + score + " y tiempo: " + tiempo+ " segundos");

        Button inicio = (Button) findViewById(R.id.back);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
