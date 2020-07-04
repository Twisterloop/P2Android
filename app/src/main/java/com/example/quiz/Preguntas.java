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
import android.widget.Chronometer;

import java.util.Arrays;

public class Preguntas extends AppCompatActivity {

    private int resIds[] = {
            R.id.res1, R.id.res2, R.id.res3, R.id.res4
    };
    private String Contador = "%s/%s";
    private String AciertosYFallos = "A: %s F: %s";
    TextView AciertosView;
    TextView ContadorView;
    private int correcta;
    private int actual;
    private String[] preguntas;
    private TextView preguntaQuiz;
    private TextView img;
    private int[] respuestas;
    private boolean[] acierto;
    int aciertos = 0;
    int fallos = 0;
    int contador = 1;
    int tiempo;
    int numpreguntas = Configuracion.NPreg;
    Chronometer TimeView;
    private RadioGroup group;
    private Button inicio;
    private int score;

    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeView = findViewById(R.id.Timer);
        TimeView.setFormat("Tiempo: %s");
        TimeView.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                tiempo++;
            }
        });
        AciertosView = findViewById(R.id.Aciertos);
        ContadorView = findViewById(R.id.Contador);
        AciertosView.setText(String.format(AciertosYFallos,aciertos, fallos));
        ContadorView.setText(String.format(Contador,1,numpreguntas));
        TimeView.start();


        group = (RadioGroup) findViewById(R.id.grupoRes);
        preguntaQuiz = (TextView) findViewById(R.id.preguntaQuiz);

        inicio = (Button) findViewById(R.id.nuevo);
        img= (TextView) findViewById(R.id.lonk);

        preguntas = getResources().getStringArray(R.array.preguntas);
        empiezaNuevo();



        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empiezaNuevo();
            }
        });

    }
    public void resultados() {
        Intent intent = new Intent(this, Resultados.class);
        intent.putExtra(EXTRA_NUMBER, score);


        startActivity(intent);
        empiezaNuevo();

    }
    public void onRadioButtonClicked(View view){
        compruebaRes();
    }

    private void empiezaNuevo() {
        acierto= new boolean[preguntas.length];
        respuestas = new int[preguntas.length];
        Arrays.fill(respuestas, -1);
        actual = 0;
        score = 0;
        mostrarPregunta();
    }

    private void compruebaRes() {
        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for (int i = 0; i < resIds.length; i++) {
            if (resIds[i] == id) {
                ans = i;
            }
        }
        if (ans == correcta) {
            aciertos++;
            AciertosView.setText(String.format(AciertosYFallos,aciertos, fallos));
            score = score + 3;
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            if (actual < preguntas.length - 1) {
                actual++;
                mostrarPregunta();
            } else {
                TimeView.stop();
//                si queremos hacer algo con el tiempo aquí es donde hay que guardarlo o pasarlo
                resultados();

            }
        }
        else {
            fallos++;
            actual++;
            AciertosView.setText(String.format(AciertosYFallos,aciertos, fallos));
            score = score - 2;
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            mostrarPregunta();
        }
    }
    private void mostrarPregunta() {
        String k = preguntas[actual];
        String[] parts = k.split(";");
        contador++;
        ContadorView.setText(String.format(Contador,contador,numpreguntas));
        group.clearCheck();

        preguntaQuiz.setText(parts[0]);
        for (int i = 0; i < resIds.length; i++) {
            RadioButton rb = (RadioButton) findViewById(resIds[i]);
            String res = parts[i+1];
            if (res.charAt(0) == '*') {
                correcta = i;
                res = res.substring(1);
            }
            rb.setText(res);
            if (respuestas[actual] == i) {
                rb.setChecked(true);
            }
        }
        if (actual == 0) {
            inicio.setVisibility(View.GONE);
        } else {
            inicio.setVisibility(View.VISIBLE);
        }if (actual != 0) {
            img.setVisibility(View.GONE);
        } else {
            img.setVisibility(View.VISIBLE);
        }

    }
}
