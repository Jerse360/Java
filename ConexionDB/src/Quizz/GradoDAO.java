package Quizz;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;
public class GradoDAO {

    private static Conexion conexion = new Conexion();

    public static List<Grado> obtenerGrado()
    {
        List<Grado> Grados = new ArrayList<>();
        Connection con = conexion.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM grado");

            while (rs.next()){
                Grado grado = new Grado(rs.getInt("id"),rs.getInt("salon"),
                        rs.getInt("cantA"),rs.getString("nombreG"));

                Grados.add(grado);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Grados;
    }

    public static boolean agregarGrado(Grado grado)
    {
        Connection con = conexion.getConnection();
        String query = "INSERT INTO Grado (nombreG, salon, cantA) VALUE (?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, grado.getNombreG());
            pst.setInt(2, grado.getSalon());
            pst.setInt(3, grado.getCantA());

            int resultado = pst.executeUpdate();
            return resultado > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;

        }

    }


    public void eliminarGrado(int id) {

        Connection con = conexion.getConnection();
        String query = "DELETE FROM grado WHERE id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM grado WHERE id = ?");
            stmt.setInt(1,id);

            int filas = stmt.executeUpdate();

            if (filas > 0)
            {
                JOptionPane.showMessageDialog(null,"Registro eliminado exitosamente");
            }
            else;
            {
                JOptionPane.showMessageDialog(null,"No se encontro");
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) {

        GradoDAO gradoDAO = new GradoDAO();
        boolean val = true;

        do {
            int op = Integer.parseInt(JOptionPane.showInputDialog("1.Mostrar \n2.Agregar \n3.Eliminar"));

            switch (op)
            {

                case 1:
                    List<Grado> gra = GradoDAO.obtenerGrado();
                    System.out.println(("Listado de grados"));
                    System.out.println(("- - - - - - - - - - -"));

                    for (Grado grado: gra)
                    {
                        System.out.println("id: "+grado.getId());
                        System.out.println("nombreG: "+grado.getNombreG());
                        System.out.println("salon: "+grado.getSalon());
                        System.out.println("cantA: "+grado.getCantA());
                        System.out.println("- - - - - - - - - - -");

                    }
                    break;

                case 2:
                    String nombreG = JOptionPane.showInputDialog("Ingrese el nombre del salon");
                    int salon = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del salon"));
                    int cantA = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de alumnos"));


                    Grado gradoAdd = new Grado(0,salon,cantA,nombreG);

                    if (GradoDAO.agregarGrado(gradoAdd))
                    {
                        JOptionPane.showMessageDialog(null, "Grado creado con exito!!");
                    }

                    break;

                case 3:
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del grado"));
                    gradoDAO.eliminarGrado(id);
                    break;

            }
        }while(val);
    }
}
