package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz.PreguntaContract.TablaPreguntas;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PreguntasDb.db";
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;

    public PreguntaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                TablaPreguntas.TABLE_NAME + " ( " +
                TablaPreguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TablaPreguntas.PREGUNTA + " TEXT, " +
                TablaPreguntas.TIPO + " TEXT, " +
                TablaPreguntas.DIFICULTAD + " TEXT, " +
                TablaPreguntas.IMAGEN + " TEXT, " +
                TablaPreguntas.VIDEO + " TEXT, " +
                TablaPreguntas.AUDIO + " TEXT, " +
                TablaPreguntas.RES1 + " TEXT, " +
                TablaPreguntas.RES2 + " TEXT, " +
                TablaPreguntas.RES3 + " TEXT, " +
                TablaPreguntas.RES4 + " TEXT, " +
                TablaPreguntas.RES_CORRECTA + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        CrearPreguntas();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;
        db.execSQL("DROP TABLE IF EXISTS " + TablaPreguntas.TABLE_NAME);
        onCreate(db);
    }

    private void CrearPreguntas() {
        Pregunta p1 = new Pregunta("¿De que juego es esta skin de Link?", "Imagen", "Normal", "lonk", "No", "No", "The legend of Zelda", "TLOZ: Majoras Mask", "TLOZ: Ocarina of Time", "TLOZ: Breath of the Wild", 2);
        nuevaPregunta(p1);
        Pregunta p2 = new Pregunta("¿Qué personaje no pertenece a una compañía japonesa?", "Imagen", "Normal", "waluonicle", "No", "No", "Banjo", "Samus Oscura", "King K.Rool", "Waluigi", 1);
        nuevaPregunta(p2);
        Pregunta p3 = new Pregunta("¿De qué color se vuelven los ojos de los personajes al hacer un 'parry'?", "Texto", "Normal", "No", "No", "No", "Azul", "Rojo", "Verde", "Amarillo", 4);
        nuevaPregunta(p3);
        Pregunta p4 = new Pregunta("¿Cuál es el único personaje al que se le ponen los ojos de otro color?", "Texto", "Normal", "No", "No", "No", "Wario", "Meta Knight", "Ridley", "Planta Piraña", 2);
        nuevaPregunta(p4);
        Pregunta p5 = new Pregunta("¿A qué versión de Smash pertenece este vídeo?", "Video", "Normal", "No", "foxfinalsmash", "No", "Brawl", "SSBU", "3Ds/WiiU", "64", 2);
        nuevaPregunta(p5);
        Pregunta p6 = new Pregunta("¿Cuál de estos personajes NO puede recibir este ataque sin bloquear?", "Video", "Normal", "No", "ganonsmash", "No", "Yoshi", "Kirby", "Pikachu", "Luigi", 1);
        nuevaPregunta(p6);
        Pregunta p7 = new Pregunta("¿Qué dice Lucario cuando hace el Smash Final en la versión española?", "Video", "Normal", "No", "lucariofinalsmash", "No", "Pika pika", "Lucario", "Es mi momento", "Esto no ha acabado", 4);
        nuevaPregunta(p7);
        Pregunta p8 = new Pregunta("¿Qué personaje ayuda a Robin?", "Video", "Normal", "No", "robinfinalsmash", "No", "Chrom", "Math", "Ike", "Mario", 1);
        nuevaPregunta(p8);
        Pregunta p9 = new Pregunta("¿Qué ocurre con Samus después del vídeo?", "Video", "Normal", "No", "samussmashbrawl", "No", "Nada", "Muere", "Pierde la armadura", "Se duerme", 3);
        nuevaPregunta(p9);
        Pregunta p10 = new Pregunta("¿A qué saga pertenece esta canción?", "Audio", "Normal", "No", "No", "kkcruisin", "Animal Crossing", "Assassins Creed", "Banjo", "Yoshis Island", 1);
        nuevaPregunta(p10);
        Pregunta p11 = new Pregunta("¿A qué ayudante pertenece la música?", "Audio", "Normal", "No", "No", "shovelk", "Waluigi", "Papyrus", "Groudon", "Shovel Knight", 4);
        nuevaPregunta(p11);
        Pregunta p12 = new Pregunta("¿En cuál de los siguientes mapas NO puedes poner megalovania?", "Audio", "Normal", "No", "No", "megalovania", "PictoChat2", "Great Cave Offensive", "Hanenbow", "StreetPass Quest", 2);
        nuevaPregunta(p12);
        Pregunta p13 = new Pregunta("¿A qué ayudante pertenece?", "Audio", "Normal", "No", "No", "wah", "Wario", "Mario", "Bowser", "Waluigi", 4);
        nuevaPregunta(p13);
        Pregunta p14 = new Pregunta("¿Qué cambia en el juego original?", "Video", "Normal", "No", "jokerfsmash", "No", "Nada", "Sangre", "Armas", "Personajes", 2);
        nuevaPregunta(p14);
        Pregunta p15 = new Pregunta("¿Escenario base de Mario?", "Texto", "Normal", "No", "No", "No", "Peach Castle", "Saffron City", "Planet Zebes", "Pirate Ship", 1);
        nuevaPregunta(p15);
        Pregunta p16 = new Pregunta("¿Cuántos personajes jugables hay?", "Texto", "Normal", "No", "No", "No", "69", "59", "76", "75", 3);
        nuevaPregunta(p16);
        Pregunta p17 = new Pregunta("¿Qué pasa al disparar un banana gun?", "Texto", "Normal", "No", "No", "No", "Se vuelve una cáscara", "Ganas", "Te caes", "Aumenta tu potasio", 1);
        nuevaPregunta(p17);
        Pregunta p18 = new Pregunta("¿Qué tipo de animal es Bowser?", "Texto", "Normal", "No", "No", "No", "Tortuga", "Caracol", "Koopa", "Fontanero", 3);
        nuevaPregunta(p18);
        Pregunta p19 = new Pregunta("¿Cuál es el único Neutral B que SIEMPRE hace KO al 0%?", "Texto", "Normal", "No", "No", "No", "Roy", "Ganondorf", "Byleth", "Robin", 1);
        nuevaPregunta(p19);
        Pregunta p20 = new Pregunta("¿Qué pasa si se le rompe el escudo a Jigglypuff?", "Texto", "Experto", "No", "No", "No", "Se aturde", "Nada", "Muere", "No puede usar el escudo", 3);
        nuevaPregunta(p20);
        Pregunta p21 = new Pregunta("¿Quíen anda más rápido?", "Texto", "Experto", "No", "No", "No", "Marth", "Sonic", "Cpt Falcon", "Little Mac", 1);
        nuevaPregunta(p21);
        Pregunta p22 = new Pregunta("¿Por qué no estaban los Ice Climbers en la 3ds?", "Texto", "Experto", "No", "No", "No", "Estaban OP", "Se olvidó Sakurai", "No querían", "El motor no lo permitía", 4);
        nuevaPregunta(p22);
        Pregunta p23 = new Pregunta("¿Quíen es el más rápido en el aire?", "Texto", "Experto", "No", "No", "No", "Wario", "Jigglypuff", "Yoshi", "Kirby", 3);
        nuevaPregunta(p23);
        Pregunta p24 = new Pregunta("¿Quién de estos recibe daño con su Counter?", "Texto", "Experto", "No", "No", "No", "Incineroar", "Marth", "K K Rool", " Ike", 1);
        nuevaPregunta(p24);


    }

    private void nuevaPregunta(Pregunta p) {
        ContentValues cv = new ContentValues();
        cv.put(TablaPreguntas.PREGUNTA, p.getPregunta());
        cv.put(TablaPreguntas.TIPO, p.getTipo());
        cv.put(TablaPreguntas.DIFICULTAD, p.getDificultad());
        cv.put(TablaPreguntas.IMAGEN, p.getImagen());
        cv.put(TablaPreguntas.VIDEO, p.getVideo());
        cv.put(TablaPreguntas.AUDIO, p.getAudio());
        cv.put(TablaPreguntas.RES1, p.getRes1());
        cv.put(TablaPreguntas.RES2, p.getRes2());
        cv.put(TablaPreguntas.RES3, p.getRes3());
        cv.put(TablaPreguntas.RES4, p.getRes4());
        cv.put(TablaPreguntas.RES_CORRECTA, p.getRes_correcta());
        db.insert(TablaPreguntas.TABLE_NAME, null, cv);
    }

    public List<Pregunta> getTodas() {
        List<Pregunta> listaPreguntas = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TablaPreguntas.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Pregunta p = new Pregunta();
                p.setPregunta(c.getString(c.getColumnIndex(TablaPreguntas.PREGUNTA)));
                p.setTipo(c.getString(c.getColumnIndex(TablaPreguntas.TIPO)));
                p.setDificultad(c.getString(c.getColumnIndex(TablaPreguntas.DIFICULTAD)));
                p.setImagen(c.getString(c.getColumnIndex(TablaPreguntas.IMAGEN)));
                p.setVideo(c.getString(c.getColumnIndex(TablaPreguntas.VIDEO)));
                p.setAudio(c.getString(c.getColumnIndex(TablaPreguntas.AUDIO)));
                p.setRes1(c.getString(c.getColumnIndex(TablaPreguntas.RES1)));
                p.setRes2(c.getString(c.getColumnIndex(TablaPreguntas.RES2)));
                p.setRes3(c.getString(c.getColumnIndex(TablaPreguntas.RES3)));
                p.setRes4(c.getString(c.getColumnIndex(TablaPreguntas.RES4)));
                p.setRes_correcta(c.getInt(c.getColumnIndex(TablaPreguntas.RES_CORRECTA)));
                listaPreguntas.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return listaPreguntas;
    }
}
