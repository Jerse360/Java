import Conexion.Conexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO
{
    private Conexion conexion = new Conexion();
    //Mostrar datos

    public List<Alumno> obtenerAlumno()
    {
        List<Alumno> alumnos = new ArrayList<>();
        Connection con = conexion.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumno");

            while (rs.next()){
                Alumno alumno = new Alumno(rs.getInt("nAlum"),
                        rs.getString("nombre"), rs.getString("apellido"),
                                rs.getString("direccion"),rs.getString("telefono"));

                alumnos.add(alumno);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return alumnos;
    }


    public  boolean agregarAlumno(Alumno alumno)
    {
        Connection con = conexion.getConnection();
        String query = "INSERT INTO Alumno (nombre, apellido, direccion, telefono) VALUE (?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, alumno.getNombre());
            pst.setString(2, alumno.getApellido());
            pst.setString(3, alumno.getDireccion());
            pst.setString(4, alumno.getTelefono());

            int resultado = pst.executeUpdate();
            return resultado > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;

        }

    }

    //Eliminar

    public List<Alumno> eliminarAlumno(int id) {

        Connection con = conexion.getConnection();
        String query = "DELETE FROM alumno WHERE nAlum = ?";

        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM alumno WHERE nAlum = ?");
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


    public void actualizarGUI(int id, String nombre, String apellido, String direccion, String telefono) {
        Connection con = conexion.getConnection();
        String query = "UPDATE alumno SET nombre = ?, apellido = ?, direccion = ?, telefono = ? where nAlum = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1,nombre);
            stmt.setString(2,apellido);
            stmt.setString(3,direccion);
            stmt.setString(4,telefono);
            stmt.setInt(5,id);
            int filas = stmt.executeUpdate();

            if (filas > 0)
                JOptionPane.showMessageDialog(null,"Alumno actualizado exitosamente");
            else
                JOptionPane.showMessageDialog(null,"No se encontro el alumno");
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }



    public void actualizarAlumno(int id, String val){
        Connection con = conexion.getConnection();
        int op = Integer.parseInt(JOptionPane.showInputDialog("Actualizar\n1.Nombre\n2.Apellido\n3.Direccion\n4.Telefono"));
        String query = "";
        String seleccion = "";

        switch (op)
        {
            case 1:
                seleccion = "nombre";
                query = "UPDATE alumno SET "+seleccion+" = ? WHERE nAlum = ?";
                break;
            case 2:
                seleccion = "apellido";
                query = "UPDATE alumno SET "+seleccion+" = ? WHERE nAlum = ?";
                break;
            case 3:
                seleccion = "direccion";
                query = "UPDATE alumno SET "+seleccion+" = ? WHERE nAlum = ?";
                break;
            case 4:
                seleccion = "telefono";
                query = "UPDATE alumno SET "+seleccion+" = ? WHERE nAlum = ?";
                break;

        }

        try
        {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,val);
            stmt.setInt(2,id);

            int filas = stmt.executeUpdate();

            if (filas > 0)
                JOptionPane.showMessageDialog(null,"Alumno actualizado exitosamente");
            else
                JOptionPane.showMessageDialog(null,"No se encontro el alumno");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<Alumno> buscarAlumno(int id)
    {
        List<Alumno> alumnos = new ArrayList<>();
        Connection con = conexion.getConnection();

        StringBuffer stringBuffer = new StringBuffer();


        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumno WHERE nAlum = "+id);

            while (rs.next()){
                Alumno alumno = new Alumno(rs.getInt("nAlum"),
                        rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("direccion"),rs.getString("telefono"));

                alumnos.add(alumno);
            }

            if (alumnos.isEmpty())
                JOptionPane.showMessageDialog(null,"Alumno no encontrado");
            else
            {
                stringBuffer.append("Listado de alumnos");
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
        return alumnos;
    }

    public static void main(String[] args) {

        AlumnoDAO alumnoDAO = new AlumnoDAO();
        boolean val = true;

        do {
            int op = Integer.parseInt(JOptionPane.showInputDialog("1.Mostrar \n2.Agregar \n 3.Eliminar \n 4.Actualizar \n 5.Buscar"));

            switch (op)
            {

                case 1:
                    List<Alumno> alum = alumnoDAO.obtenerAlumno();
                    System.out.println(("Listado de alumnos"));
                    System.out.println(("- - - - - - - - - - -"));

                    for (Alumno alumno: alum)
                    {
                        System.out.println("nAlum: "+alumno.getnAlum());
                        System.out.println("nombre: "+alumno.getNombre());
                        System.out.println("apellido: "+alumno.getApellido());
                        System.out.println("direccion: "+alumno.getDireccion());
                        System.out.println("telefono: "+alumno.getTelefono());
                        System.out.println("- - - - - - - - - - -");

                    }
                    break;

                case 2:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                    String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
                    String direccion = JOptionPane.showInputDialog("Ingrese la direccion");
                    String telefono = JOptionPane.showInputDialog("Ingrese el telefono");

                    Alumno alumnoAdd = new Alumno(0,nombre,apellido,direccion,telefono);

                    if (alumnoDAO.agregarAlumno(alumnoAdd))
                    {
                        JOptionPane.showMessageDialog(null, "Alumno creado con exito!!");
                    }

                    break;

                case 3:
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del alumno"));
                    alumnoDAO.eliminarAlumno(id);
                    break;

                case 4:
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del alumno"));
                    String valor = JOptionPane.showInputDialog("Ingrese el valor a actualizar");
                    alumnoDAO.actualizarAlumno(id,valor);
                    break;

                case 5:
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del alumno"));
                    alumnoDAO.buscarAlumno(id);
                    break;

                case 6:
                    val = false;
                    break;

            }
        }while (val);

    }

}
