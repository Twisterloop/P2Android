package com.example.quiz;

import android.provider.BaseColumns;

public final class PreguntaContract {

    private PreguntaContract() {
    }

    public static class TablaPreguntas implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String PREGUNTA = "pregunta";
        public static final String TIPO = "tipo";
        public static final String DIFICULTAD = "dificultad";
        public static final String IMAGEN = "imagen";
        public static final String VIDEO = "video";
        public static final String AUDIO = "audio";
        public static final String RES1 = "res1";
        public static final String RES2 = "res2";
        public static final String RES3 = "res3";
        public static final String RES4 = "res4";
        public static final String RES_CORRECTA = "resCorrecta";
    }
}
