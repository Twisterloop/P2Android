package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;
import android.widget.VideoView;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Preguntas extends AppCompatActivity {

    private int[] resIds = {
            R.id.res1, R.id.res2, R.id.res3, R.id.res4
    };

    private String Contador = "%s/%s";
    private String AciertosYFallos = "A: %s F: %s";
    TextView AciertosView;
    TextView ContadorView;

    int aciertos = 0;
    int fallos = 0;
    public static int tiempo;
    Chronometer TimeView;
    int contador = 0;
    int numpreguntas = Configuracion.NPreg;

    private boolean acabado = false;

    public static int score = 0;

    private Pregunta actual;
    private TextView preguntaQuiz;

    private List<Pregunta> listaPreguntas;

    private VideoView vid;
    private ImageView img;
    MediaPlayer player;

    private RadioGroup group;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acabado = false;
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
        AciertosView.setText(String.format(AciertosYFallos, aciertos, fallos));
        ContadorView.setText(String.format(Contador, 1, numpreguntas));
        TimeView.start();

        group = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.res1);
        rb2 = findViewById(R.id.res2);
        rb3 = findViewById(R.id.res3);
        rb4 = findViewById(R.id.res4);
        preguntaQuiz = findViewById(R.id.preguntaQuiz);

        img = findViewById(R.id.imagen);
        vid = findViewById(R.id.videoPregunta);

        PreguntaDbHelper dbHelper = new PreguntaDbHelper(this);
        listaPreguntas = dbHelper.getTodas();

        if (!Configuracion.expertoCheck) {
            for (Iterator<Pregunta> iterator = listaPreguntas.iterator(); iterator.hasNext(); ) {
                Pregunta p = iterator.next();
                if (p.getDificultad().equals("Experto")) {
                    iterator.remove();
                }
            }
        }

        Collections.shuffle(listaPreguntas);
        mostrarPregunta();
    }

    public void resultados() {
        Intent intent = new Intent(this, Resultados.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        if (!acabado)
            acabado = true;
        compruebaRes();
    }

    private void compruebaRes() {
        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for (int i = 0; i < resIds.length; i++) {
            if (resIds[i] == id) ans = i + 1;
        }
        if (ans == actual.getRes_correcta()) {
            aciertos++;
            AciertosView.setText(String.format(AciertosYFallos, aciertos, fallos));
            score = score + 3;
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        } else {
            fallos++;
            AciertosView.setText(String.format(AciertosYFallos, aciertos, fallos));
            if (score > 0) score--;
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }
        if (contador + 1 < numpreguntas) {
            mostrarPregunta();
        } else {
            resultados();
        }
        acabado = false;
    }

    private void mostrarPregunta() {
        actual = listaPreguntas.get(contador);
        group.clearCheck();

        if (contador > numpreguntas)
            return;

        preguntaQuiz.setText(actual.getPregunta());
        ContadorView.setText(String.format(Contador, contador, numpreguntas));
        rb1.setText(actual.getRes1());
        rb2.setText(actual.getRes2());
        rb3.setText(actual.getRes3());
        rb4.setText(actual.getRes4());
        contador++;

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

    public void MostrarTexto() {
        vid.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
    }

    public void MostrarImagen() {
        vid.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        Resources res = getResources();
        int imgId = res.getIdentifier(actual.getImagen(), "drawable", getPackageName());
        img.setImageResource(imgId);
    }

    public void MostrarVideo() {
        vid.setVisibility(View.VISIBLE);
        img.setVisibility(View.INVISIBLE);
        Resources res = getResources();
        int vidId = res.getIdentifier(actual.getVideo(), "raw", getPackageName());
        String videoPath = "android.resource://" + getPackageName() + "/" + vidId;
        Uri uri = Uri.parse((videoPath));
        vid.setVideoURI(uri);

        vid.start();
    }

    public void MostrarAudio() {
        vid.setVisibility(View.GONE);
        img.setVisibility(View.INVISIBLE);
        play();
    }

    public void play() {
        Resources res = getResources();
        int audioId = res.getIdentifier(actual.getAudio(), "raw", getPackageName());
        player = MediaPlayer.create(this, audioId);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.release();
                player = null;
            }
        });
        player.start();
    }
}
