package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Configuracion extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    public static int NPreg = 5;
    public static boolean expertoCheck= false;
    public static String nombreJug = "Anónimo";
    private TextView prueba;
    private CheckBox experto;
    private EditText nombre;
    private RadioGroup preguntasR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        preguntasR = (RadioGroup) findViewById(R.id.radioPreguntas);
        experto = findViewById(R.id.expert);
        nombre = findViewById(R.id.nombre);
        prueba = findViewById(R.id.prueba);

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

        if(!fileExists(this, "Configuraciones.txt")) {
            WriteBtn();
        }
        ReadBtn();
        if (expertoCheck)
            experto.setChecked(true);
        actualizarText();
    }


    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    public void ReadBtn() {

        try {
            FileInputStream fileIn=openFileInput("Configuraciones.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            String[] separador = s.split(",");
            NPreg =  Integer.parseInt(separador[0]);
            expertoCheck = Boolean.parseBoolean(separador[1]);
            nombreJug = separador[2];



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onExperto(View view) {

        if (experto.isChecked()){
            expertoCheck = true;
        }else{
            expertoCheck=false;
        }
        actualizarText();
    }

    public void onAceptar(View view) {
        String temp = nombre.getText().toString();
        if(temp.length() > 0){
            nombreJug = temp;
        }else{
            nombreJug="Anónimo";
        }
        actualizarText();
        closeKeyboard();
    }

    public void actualizarText(){
        prueba.setText(Html.fromHtml("<b>Nombre del Jugador: </b>" + nombreJug +"<br><br>" + "<b>Modo Experto: </b>" +  expertoCheck + "<br><br>"+ "<b>Nº de Preguntas: </b>" + NPreg));
    }

    public void onConfirm(View view) {
        WriteBtn();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMan = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMan.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
        actualizarText();
    }

    public void WriteBtn(){
        try {
            FileOutputStream fileout = openFileOutput("Configuraciones.txt", MODE_PRIVATE);
            String outputConf = NPreg+","+expertoCheck+","+nombreJug;
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(outputConf);
            outputWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
