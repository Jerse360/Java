package Quizz;
import Conexion.Conexion;
public class Grado {

    int id, salon, cantA;
    String nombreG;

    public Grado(int id, int salon, int cantA, String nombreG) {
        this.id = id;
        this.salon = salon;
        this.cantA = cantA;
        this.nombreG = nombreG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalon() {
        return salon;
    }

    public void setSalon(int salon) {
        this.salon = salon;
    }

    public int getCantA() {
        return cantA;
    }

    public void setCantA(int cantA) {
        this.cantA = cantA;
    }

    public String getNombreG() {
        return nombreG;
    }

    public void setNombreG(String nombreG) {
        this.nombreG = nombreG;
    }
}
