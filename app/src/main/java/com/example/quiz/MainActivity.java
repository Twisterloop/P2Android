package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    //Variable para Saber desde dónde se accede al ranking
    public static boolean desdeMenu = false;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //Leemos el archivo de configuración

        if (!fileExists(this, "Configuraciones.txt")) {
            WriteBtn();
        }

        ReadBtn();
    }

    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    //Lee el fichero

    public void ReadBtn() {

        try {
            FileInputStream fileIn = openFileInput("Configuraciones.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            String[] separador = s.split(",");
            Configuracion.NPreg = Integer.parseInt(separador[0]);
            Configuracion.expertoCheck = Boolean.parseBoolean(separador[1]);
            Configuracion.nombreJug = separador[2];


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Crea o modifica el fichero

    public void WriteBtn() {
        try {
            FileOutputStream fileout = openFileOutput("Configuraciones.txt", MODE_PRIVATE);
            String outputConf =  Configuracion.NPreg + "," +  Configuracion.expertoCheck + "," +  Configuracion.nombreJug;
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(outputConf);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Comenzar(View view) {
        if(Configuracion.nombreJug.equals("Anónimo")){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No te has puesto nombre\n ¿Quieres continuar?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Si",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), Preguntas.class);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else {
            Intent intent = new Intent(this, Preguntas.class);
            startActivity(intent);
        }
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
