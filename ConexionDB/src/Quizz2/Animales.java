package Quizz2;

public class Animales {

    int nAni, edad;
    String nombre, especie, peso, habitat;

    public Animales(int nAni, int edad, String nombre, String especie, String peso, String habitat) {
        this.nAni = nAni;
        this.edad = edad;
        this.nombre = nombre;
        this.especie = especie;
        this.peso = peso;
        this.habitat = habitat;
    }

    public int getnAni() {
        return nAni;
    }

    public void setnAni(int nAni) {
        this.nAni = nAni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
