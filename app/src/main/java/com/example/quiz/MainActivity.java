package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    //Variable para Saber desde dónde se accede al ranking
    public static boolean desdeMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void Comenzar(View view) {
        Intent intent = new Intent(this, Preguntas.class);
        startActivity(intent);
    }

    public void Configuracion(View view) {
        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }

    public void Ranking(View view) {
        desdeMenu = true;
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }


}
