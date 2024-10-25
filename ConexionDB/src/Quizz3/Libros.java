package Quizz3;

import java.time.Year;

public class Libros {

    int id_libro;
    String titulo, autor, iSBM, estado;
    Year ano;

    public Libros(int id_libro, String titulo, String autor, String iSBM, String estado, Year ano) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.iSBM = iSBM;
        this.estado = estado;
        this.ano = ano;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getiSBM() {
        return iSBM;
    }

    public void setiSBM(String iSBM) {
        this.iSBM = iSBM;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Year getAno() {
        return ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }
}
