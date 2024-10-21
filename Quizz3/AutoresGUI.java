package Quizz3;

import Conexion.Conexion;
import Conexion2.Conexion2;
import Quizz2.Animales;
import Quizz2.AnimalesDAO;
import Quizz2.AnimalesGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AutoresGUI {
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTable table1;
    private JPanel main;
    private JTextField textField4;
    private  AutoresDAO autoresDAO = new AutoresDAO();
    private Conexion2 conexion2 = new Conexion2();
    public AutoresGUI() {

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField1.getText();
                String fechaNacimiento = textField2.getText();
                String nacionalidad = textField3.getText();
                int id = Integer.parseInt(textField4.getText());
                Autores autores = new Autores(nombre,fechaNacimiento,nacionalidad,id);

                if (autoresDAO.agregarAutor(autores))
                {
                    JOptionPane.showMessageDialog(null, "Autor creado con exito");
                }

                textField3.setText("");
                textField2.setText("");
                textField1.setText("");
                obtenerAnimal();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int id = Integer.parseInt(textField4.getText());
                List<Autores> autor = new AutoresDAO().eliminarAutor(id);
                Connection con = conexion2.getConnection();
                textField3.setText("");
                textField2.setText("");
                textField1.setText("");
                obtenerAnimal();

            }
        });


    }

    public void obtenerAnimal () {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Nombre completo");
        modelo.addColumn("Fecha de nacimiento");
        modelo.addColumn("Nacionalidad");

        table1.setModel(modelo);

        String[] dato = new String[3];
        Connection con = conexion2.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM autores");

            while (rs.next()){
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);


                modelo.addRow(dato);
            }
        }

        catch (SQLException e){
            e.printStackTrace();

        }



    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("CRUD ALUMNO");
        frame.setContentPane(new AutoresGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
