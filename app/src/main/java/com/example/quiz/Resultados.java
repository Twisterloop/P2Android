package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Resultados extends AppCompatActivity {

    public String[] soluciones = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        TextView results = findViewById(R.id.results);


        ReadBtn();
        WriteBtn();

        results.setText("Enhorabuena, " + Configuracion.nombreJug + ", tu puntuación es:" + Preguntas.score + " y tiempo: " + Preguntas.tiempo + " segundos");

        Button inicio = findViewById(R.id.back);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    public void ReadBtn() {

        try {
            FileInputStream fileIn = openFileInput("Ranking.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            leeRanking(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WriteBtn() {
        try {
            FileOutputStream fileout = openFileOutput("Ranking.txt", MODE_PRIVATE);
            String outputConf = generaRanking();
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(outputConf);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Interpreta el fichero
    public void leeRanking(String r) {
        String[] separador = r.split(";");
        String temp = Configuracion.nombreJug + "," + Preguntas.score + "," + Preguntas.tiempo;

        for (int i = 0; i < soluciones.length; i++) {
            String[] separador2 = separador[i].split(",");
            String[] separadorTemp = temp.split(",");
            int actualRank = Integer.parseInt(separador2[1]);
            int tempRank = Integer.parseInt(separadorTemp[1]);
            //Comparamos los score
            if (actualRank < tempRank) {
                soluciones[i] = temp;
                temp = separador[i];
            } else if (actualRank == tempRank) {
                //Si los scores son iguales, se comparan tiempos
                int actualTime = Integer.parseInt(separador2[2]);
                int tempTime = Integer.parseInt(separadorTemp[2]);
                if (actualTime > tempTime) {
                    soluciones[i] = temp;
                    temp = separador[i];
                } else {
                    soluciones[i] = separador[i];
                }
            } else {
                soluciones[i] = separador[i];
            }
        }
    }

    //Genera el String para el fichero
    public String generaRanking() {
        String res = "";
        for (int i = 0; i < soluciones.length - 1; i++) {
            res += soluciones[i] + ";";
        }
        res += soluciones[soluciones.length - 1];
        return res;
    }

    public void Ranking(View view) {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
}
