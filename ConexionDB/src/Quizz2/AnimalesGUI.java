package Quizz2;
import Conexion.Conexion;
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

public class AnimalesGUI {
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JButton buscarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel main;
    private JButton informeButton;
    private JTextField textField7;
    private JTable table1;
    private JTextPane textPane1;
    private Conexion conexion = new Conexion();
    private AnimalesDAO animalesDAO = new AnimalesDAO();

    int filas;
    public AnimalesGUI() {

        this.obtenerAnimal();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField6.getText());
                List<Animales> animales = new AnimalesDAO().buscarAnimal(id);
                Connection con = conexion.getConnection();

                textField5.setText(animales.get(0).getNombre());
                textField4.setText(animales.get(0).getEspecie());
                textField3.setText(String.valueOf(animales.get(0).getEdad()));
                textField2.setText(animales.get(0).getPeso());
                textField1.setText(animales.get(0).getHabitat());

                obtenerAnimal();
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField5.getText();
                String especie = textField4.getText();
                int edad = Integer.parseInt(textField3.getText());
                String peso = textField2.getText();
                String habitat = textField1.getText();

                Animales animales = new Animales(0,edad,nombre,especie,peso,habitat);

                if (animalesDAO.agregarAnimal(animales))
                {
                    JOptionPane.showMessageDialog(null, "Animal creado con exito!!");
                }
                textField5.setText("");
                textField4.setText("");
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

                int id = Integer.parseInt(textField6.getText());
                List<Animales> alum = new AnimalesDAO().eliminarAnimal(id);
                Connection con = conexion.getConnection();
                textField6.setText("");
                textField5.setText("");
                textField4.setText("");
                textField3.setText("");
                textField2.setText("");
                textField1.setText("");
                obtenerAnimal();

            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField6.getText());

                String nombre = textField5.getText();
                String especie = textField4.getText();
                int edad = Integer.parseInt(textField3.getText());
                String peso = textField2.getText();
                String habitat = textField1.getText();
                AnimalesDAO alm = new AnimalesDAO();
                alm.actualizarGUI(id,nombre,especie,edad,peso,habitat);
                obtenerAnimal();
            }
        });

        informeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int informe = Integer.parseInt(textField7.getText());
                List<Animales> animales = new AnimalesDAO().informe(informe);
                Connection con = conexion.getConnection();
                StringBuffer stringBuffer = new StringBuffer();
                if (animales.isEmpty())
                    JOptionPane.showMessageDialog(null,"Animal no encontrado");
                else
                {
                    stringBuffer.append("Listado de animales");
                    stringBuffer.append("\n - - - - - - - - - -");

                    for (Animales animal: animales)
                    {
                        stringBuffer.append("\nnAni: "+animal.getnAni());
                        stringBuffer.append("\nnombre: "+animal.getNombre());
                        stringBuffer.append("\nespecie: "+animal.getEspecie());
                        stringBuffer.append("\nedad: "+animal.getEdad());
                        stringBuffer.append("\npeso: "+animal.getPeso());
                        stringBuffer.append("\nhabitat: "+animal.getHabitat());
                        stringBuffer.append("\n- - - - - - - - - - - ");
                    }
                    textPane1.setText(String.valueOf(stringBuffer));

                }
                obtenerAnimal();

            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int seleccionarFlas = table1.getSelectedRow();
                if (seleccionarFlas >= 0){
                    textField6.setText((String)table1.getValueAt(seleccionarFlas, 0));
                    textField5.setText((String)table1.getValueAt(seleccionarFlas, 1));
                    textField4.setText((String)table1.getValueAt(seleccionarFlas, 2));
                    textField3.setText((String)table1.getValueAt(seleccionarFlas, 3));
                    textField2.setText((String)table1.getValueAt(seleccionarFlas, 4));
                    textField1.setText((String)table1.getValueAt(seleccionarFlas, 5));

                    filas = seleccionarFlas;
                }
            }
        });

    }

    public void obtenerAnimal () {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("nAlim");
        modelo.addColumn("nombre");
        modelo.addColumn("especie");
        modelo.addColumn("edad");
        modelo.addColumn("peso");
        modelo.addColumn("habitat");

        table1.setModel(modelo);

        String[] dato = new String[6];
        Connection con = conexion.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM animales");

            while (rs.next()){
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);

                modelo.addRow(dato);
            }
        }

        catch (SQLException e){
            e.printStackTrace();

        }



    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("CRUD ALUMNO");
        frame.setContentPane(new AnimalesGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
