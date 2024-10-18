package Quizz2;
import Conexion.Conexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalesDAO {

    public Quizz2.AnimalesDAO AnimalesDAO;
    private Conexion conexion = new Conexion();

    public  boolean agregarAnimal(Animales animales)
    {
        Connection con = conexion.getConnection();
        String query = "INSERT INTO Animales (nombre, especie, edad, peso, habitat) VALUE (?,?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, animales.getNombre());
            pst.setString(2, animales.getEspecie());
            pst.setInt(3, animales.getEdad());
            pst.setString(4, animales.getPeso());
            pst.setString(5, animales.getHabitat());

            int resultado = pst.executeUpdate();
            return resultado > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;

        }

    }

    public List<Animales> buscarAnimal(int id)
    {
        List<Animales> animal = new ArrayList<>();
        Connection con = conexion.getConnection();

        StringBuffer stringBuffer = new StringBuffer();


        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Animales WHERE nAni = "+id);

            while (rs.next()){
                Animales animales = new Animales(rs.getInt("nAni"),
                        rs.getInt("edad"), rs.getString("nombre"),
                        rs.getString("especie"),rs.getString("peso"), rs.getString("habitat"));

                animal.add(animales);
            }

            if (animal.isEmpty())
                JOptionPane.showMessageDialog(null,"Animal no encontrado");
            else
            {
                stringBuffer.append("Listado de animales");
                stringBuffer.append("\n - - - - - - - - - -");

               /* for (Alumno alumno: alumnos)
                {
                    stringBuffer.append("\nnAlum: "+alumno.getnAlum());
                    stringBuffer.append("\nnombre: "+alumno.getNombre());
                    stringBuffer.append("\napellido: "+alumno.getApellido());
                    stringBuffer.append("\ndireccion: "+alumno.getDireccion());
                    stringBuffer.append("\ntelefono: "+alumno.getTelefono());
                    stringBuffer.append("\n- - - - - - - - - - - ");
                }
                JOptionPane.showMessageDialog(null,stringBuffer);*/

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return animal;
    }

    public List<Animales> eliminarAnimal(int id) {

        Connection con = conexion.getConnection();
        String query = "DELETE FROM animales WHERE nAni = ?";

        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM animales WHERE nAni = ?");
            stmt.setInt(1,id);

            int filas = stmt.executeUpdate();

            if (filas > 0)
            {
                JOptionPane.showMessageDialog(null,"Registro eliminado exitosamente");
            }
            /*else;
            {
                JOptionPane.showMessageDialog(null,"No se encontro");
            }*/
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return null;
    }

    public void actualizarGUI(int nAni, String nombre, String especie, int edad, String peso,String habitat) {
        Connection con = conexion.getConnection();
        String query = "UPDATE animales SET nombre = ?, especie = ?, edad = ?, peso = ?, habitat = ? WHERE nAni = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1,nombre);
            stmt.setString(2,especie);
            stmt.setInt(3,edad);
            stmt.setString(4,peso);
            stmt.setString(5,habitat);
            stmt.setInt(6,nAni);
            int filas = stmt.executeUpdate();

            if (filas > 0)
                JOptionPane.showMessageDialog(null,"Animal actualizado exitosamente");
            else
                JOptionPane.showMessageDialog(null,"No se encontro el animal");
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<Animales> informe(int informe)
    {
        List<Animales> animal = new ArrayList<>();
        Connection con = conexion.getConnection();

        StringBuffer stringBuffer = new StringBuffer();


        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Animales WHERE peso AND edad > "+informe);

            while (rs.next()){
                Animales animales = new Animales(rs.getInt("nAni"),
                        rs.getInt("edad"), rs.getString("nombre"),
                        rs.getString("especie"),rs.getString("peso"), rs.getString("habitat"));

                animal.add(animales);
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return animal;
    }
}
