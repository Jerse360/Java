package Quizz3;

import Conexion.Conexion;
import Conexion2.Conexion2;
import Quizz2.Animales;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AutoresDAO {
    public Quizz3.AutoresDAO autoresDAO;
    private Conexion2 conexion2 = new Conexion2();

    public  boolean agregarAutor(Autores autores)
    {
        Connection con = conexion2.getConnection();
        String query = "INSERT INTO autores (nombreCompleto, fecha_nacimiento, nacionalidad) VALUE (?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, autores.getNombreCompleto());
            pst.setString(2, autores.getFecha_nacimiento());
            pst.setString(3, autores.getNacionalidad());

            int resultado = pst.executeUpdate();
            return resultado > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;

        }

    }
    public List<Autores> eliminarAutor(int id) {

        Connection con = conexion2.getConnection();
        String query = "DELETE FROM animales WHERE nAni = ?";

        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM autores WHERE id = ?");
            stmt.setInt(1, id);

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


}
