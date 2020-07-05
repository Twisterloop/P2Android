package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;
import android.widget.VideoView;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Preguntas extends AppCompatActivity {

    private int[] resIds = {
            R.id.res1, R.id.res2, R.id.res3, R.id.res4
    };
    private String Contador = "%s/%s";
    private String AciertosYFallos = "A: %s F: %s";
    TextView AciertosView;
    TextView ContadorView;



    private Pregunta actual;
    private TextView preguntaQuiz;

    private VideoView vid;
    private ImageView img;
    private Button play;

    private boolean[] acierto;
    int aciertos = 0;
    int fallos = 0;
    public static int tiempo;
    Chronometer TimeView;
    int contador = 0;
    int numpreguntas = Configuracion.NPreg;

    private RadioGroup group;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button inicio;
    public static int score = 0;

    MediaPlayer player;

    private List<Pregunta> listaPreguntas;

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


        group =  findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.res1);
        rb2 = findViewById(R.id.res2);
        rb3 = findViewById(R.id.res3);
        rb4 = findViewById(R.id.res4);
        preguntaQuiz = findViewById(R.id.preguntaQuiz);

        inicio = findViewById(R.id.nuevo);
        img = findViewById(R.id.imagen);
        vid= findViewById(R.id.videoPregunta);
        play = findViewById(R.id.audio);


        PreguntaDbHelper dbHelper = new PreguntaDbHelper(this);
        listaPreguntas = dbHelper.getTodas();

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
        startActivity(intent);
        empiezaNuevo();

    }
    public void onRadioButtonClicked(View view){
        compruebaRes();
        if(actual.getTipo().equals("Audio")){
            //player.stop();
            Toast.makeText(this, "jaj", Toast.LENGTH_SHORT).show();
        }
    }

    private void empiezaNuevo() {
        //acierto= new boolean[preguntas.length];
        contador = 0;
        Collections.shuffle(listaPreguntas);
        //score = 0;
        mostrarPregunta();
    }

    private void compruebaRes() {

        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for (int i = 0; i < resIds.length; i++) {
            if (resIds[i] == id) {
                ans = i+1;
            }
        }
        if (ans == actual.getRes_correcta()) {
            aciertos++;
            AciertosView.setText(String.format(AciertosYFallos,aciertos, fallos));
            score = score + 3;
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            if (contador+1 < numpreguntas) {
                mostrarPregunta();
            } else {
                resultados();
            }
        }
        else {
            fallos++;
            AciertosView.setText(String.format(AciertosYFallos,aciertos, fallos));
            //score = score - 2;
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            mostrarPregunta();
        }
    }
    private void mostrarPregunta() {


        actual = listaPreguntas.get(contador);

        group.clearCheck();

        if(contador > numpreguntas)
            return;

        preguntaQuiz.setText(actual.getPregunta());

        ContadorView.setText(String.format(Contador, contador, numpreguntas));

        rb1.setText(actual.getRes1());
        rb2.setText(actual.getRes2());
        rb3.setText(actual.getRes3());
        rb4.setText(actual.getRes4());

        contador++;

        if (contador == 1) {
            inicio.setVisibility(View.GONE);
        } else {
            inicio.setVisibility(View.VISIBLE);
        }
        switch (actual.getTipo()) {
            case ("Texto"):
                MostrarTexto();
                break;
            case ("Imagen"):
                MostrarImagen();
                break;
            case ("Video"):
                MostrarVideo();
                break;
            case ("Audio"):
                MostrarAudio();
                break;
            default:
                Toast t = Toast.makeText(this, "No tiene tipo", Toast.LENGTH_SHORT);
                t.show();
                break;
        }
    }
    public void MostrarTexto(){
        vid.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
        play.setVisibility(View.GONE);
    }
    public void MostrarImagen(){
        vid.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        play.setVisibility(View.GONE);
        Resources res = getResources();
        int imgId = res.getIdentifier(actual.getImagen(), "drawable", getPackageName());
        img.setImageResource(imgId);
    }
    public void MostrarVideo(){
        vid.setVisibility(View.VISIBLE);
        img.setVisibility(View.GONE);
        play.setVisibility(View.GONE);
        Resources res = getResources();
        int vidId = res.getIdentifier(actual.getVideo(), "raw", getPackageName());
        String videoPath = "android.resource://" + getPackageName() + "/" + vidId;
        Uri uri = Uri.parse((videoPath));
        vid.setVideoURI(uri);

        vid.start();
    }
    public void MostrarAudio(){
        vid.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
        play.setVisibility(View.VISIBLE);
        play();
    }
    public void play(){
            Resources res = getResources();
            int audioId = res.getIdentifier(actual.getAudio(), "raw", getPackageName());
            player = MediaPlayer.create(this, audioId);

        player.start();
    }


}
