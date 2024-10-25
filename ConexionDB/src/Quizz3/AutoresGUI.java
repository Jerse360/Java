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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    int filas;
    public AutoresGUI() {
        obtenerAutor();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField1.getText();
                String fechaNacimiento = textField2.getText();
                String nacionalidad = textField3.getText();


                Autores autores = new Autores(nombre,fechaNacimiento,nacionalidad,0);

                if (autoresDAO.agregarAutor(autores))
                {
                    JOptionPane.showMessageDialog(null, "Autor creado con exito");
                }

                textField3.setText("");
                textField2.setText("");
                textField1.setText("");

                obtenerAutor();
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

                obtenerAutor();

            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField4.getText());

                String nombre = textField1.getText();
                String fecha = textField2.getText();
                String nacionalidad = textField3.getText();

                AutoresDAO alm = new AutoresDAO();
                alm.updateGUI(nombre,fecha,nacionalidad,id);
                obtenerAutor();
            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int seleccionarFlas = table1.getSelectedRow();
                if (seleccionarFlas >= 0){


                    textField4.setText((String)table1.getValueAt(seleccionarFlas, 0));
                    textField3.setText((String)table1.getValueAt(seleccionarFlas, 3));
                    textField2.setText((String)table1.getValueAt(seleccionarFlas, 2));
                    textField1.setText((String)table1.getValueAt(seleccionarFlas, 1));

                    filas = seleccionarFlas;
                }
            }
        });
    }



    public void obtenerAutor () {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre completo");
        modelo.addColumn("Fecha de nacimiento");
        modelo.addColumn("Nacionalidad");

        table1.setModel(modelo);

        String[] dato = new String[4];
        Connection con = conexion2.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM autores");

            while (rs.next()){
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);


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
