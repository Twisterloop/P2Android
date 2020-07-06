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

        TextView results = findViewById(R.id.results);

        results.setText("Enhorabuena, " + Configuracion.nombreJug + " tu puntuación es:" + Preguntas.score + " y tiempo: " + Preguntas.tiempo+ " segundos");

        Button inicio = findViewById(R.id.back);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void Ranking(View view){
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
}
