package com.example.quiz;

public class Pregunta {
    private String pregunta;
    private String tipo;
    private String dificultad;
    private String imagen;
    private String video;
    private String audio;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private int res_correcta;

    public Pregunta() {
    }

    public Pregunta(String pregunta, String tipo, String dificultad, String imagen, String video, String audio, String res1, String res2, String res3, String res4, int res_correcta) {
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.imagen = imagen;
        this.video = video;
        this.audio = audio;
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
        this.res4 = res4;
        this.res_correcta = res_correcta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getRes1() {
        return res1;
    }

    public void setRes1(String res1) {
        this.res1 = res1;
    }

    public String getRes2() {
        return res2;
    }

    public void setRes2(String res2) {
        this.res2 = res2;
    }

    public String getRes3() {
        return res3;
    }

    public void setRes3(String res3) {
        this.res3 = res3;
    }

    public String getRes4() {
        return res4;
    }

    public void setRes4(String res4) {
        this.res4 = res4;
    }

    public int getRes_correcta() {
        return res_correcta;
    }

    public void setRes_correcta(int res_correcta) {
        this.res_correcta = res_correcta;
    }
}
