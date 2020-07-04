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

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Configuracion extends AppCompatActivity {

    public static int NPreg = 5;
    RadioGroup preguntasR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        preguntasR = (RadioGroup) findViewById(R.id.radioPreguntas);

        switch (NPreg){
            case 5:
                preguntasR.check(R.id.preg5);
                break;
            case 10:
                preguntasR.check(R.id.preg10);
                break;
            case 15:
                preguntasR.check(R.id.preg15);
                break;
        }
    }
    public void onRadioButtonClicked(View view) {

        int id = preguntasR.getCheckedRadioButtonId();

        switch (id) {
            case R.id.preg5:
                NPreg = 5;
                break;
            case R.id.preg10:
                NPreg = 10;
                break;
            case R.id.preg15:
                NPreg = 15;
                break;
            default:
                NPreg = 5;
                break;
        }

        WriteBtn();
    }

    public void WriteBtn(){
        try {
            FileOutputStream fileout = openFileOutput("Configuraciones.txt", MODE_PRIVATE);
            String outputConf = String.valueOf(NPreg);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(outputConf);
            outputWriter.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
