package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Ranking extends AppCompatActivity {

    private TextView pos1;
    private TextView pos2;
    private TextView pos3;
    private TextView pos4;
    private TextView pos5;
    public String[] soluciones = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //Texto por defecto
        Arrays.fill(soluciones, "-,0,0");

        if (!fileExists(this, "Ranking.txt")) WriteBtn();

        ReadBtn();
        WriteBtn();

        pos1 = findViewById(R.id.rank1);
        pos2 = findViewById(R.id.rank2);
        pos3 = findViewById(R.id.rank3);
        pos4 = findViewById(R.id.rank4);
        pos5 = findViewById(R.id.rank5);

        asignaTextos();
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
        ;
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

    public void asignaTextos() {
        pos1.setText((soluciones[0].split(","))[0] + " " + (soluciones[0].split(","))[1]);
        pos2.setText((soluciones[1].split(","))[0] + " " + (soluciones[1].split(","))[1]);
        pos3.setText((soluciones[2].split(","))[0] + " " + (soluciones[2].split(","))[1]);
        pos4.setText((soluciones[3].split(","))[0] + " " + (soluciones[3].split(","))[1]);
        pos5.setText((soluciones[4].split(","))[0] + " " + (soluciones[4].split(","))[1]);
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
}
