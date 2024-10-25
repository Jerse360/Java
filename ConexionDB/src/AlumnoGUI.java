import Conexion.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoGUI {
    private JButton buscarButton;
    private JButton modificarButtom;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JPanel main;
    private Conexion conexion = new Conexion();
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public AlumnoGUI(){

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField5.getText());
                List<Alumno> alumnos = new AlumnoDAO().buscarAlumno(id);
                Connection con = conexion.getConnection();

                textField4.setText(alumnos.get(0).getNombre());
                textField3.setText(alumnos.get(0).getApellido());
                textField1.setText(alumnos.get(0).getDireccion());
                textField2.setText(alumnos.get(0).getTelefono());

            }

        });


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField4.getText();
                String apellido = textField3.getText();
                String direccion = textField1.getText();
                String telefono = textField2.getText();

                Alumno alumno = new Alumno(0,nombre,apellido,direccion,telefono);

                if (alumnoDAO.agregarAlumno(alumno))
                {
                    JOptionPane.showMessageDialog(null, "Alumno creado con exito!!");
                }
                textField4.setText("");
                textField3.setText("");
                textField2.setText("");
                textField1.setText("");
            }
        });




        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int id = Integer.parseInt(textField5.getText());
                List<Alumno> alum = new AlumnoDAO().eliminarAlumno(id);
                Connection con = conexion.getConnection();
                textField4.setText("");
                textField3.setText("");
                textField2.setText("");
                textField1.setText("");

            }
        });
    }

    public void obtenerGrado()
    {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("nAlum");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CRUD ALUMNO");
        frame.setContentPane(new AlumnoGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
