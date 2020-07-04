package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void Comenzar(View view){
        Intent intent = new Intent(this, Preguntas.class);
        startActivity(intent);
    }
    public void Configuracion(View view){
        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }
    


}
