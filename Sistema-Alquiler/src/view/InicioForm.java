package view;

import view.alquilables.CrearAlquilablesForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioForm extends JFrame{

    private JLabel lblTitulo;
    private JButton btnCrearAlquilables;
    private JButton btnRealizarAlquileres;
    private JButton btnReservas;
    private JButton btnGestionUsuario;
    private JPanel panel1;

    public InicioForm() {

        initComponents();

        btnRealizarAlquileres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RealizarAlquileresForm realizarAlquileresForm = new RealizarAlquileresForm();
                NavegacionForms.agregarForm(realizarAlquileresForm);
            }
        });
        btnCrearAlquilables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.agregarForm(new CrearAlquilablesForm());
            }
        });
    }

    private void initComponents(){
        setTitle("Form de Inicio");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
    }
}
