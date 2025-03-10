package view.alquilables;

import view.NavegacionForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearAlquilablesForm extends JFrame {
    private JButton btnCrearCategoria;
    private JButton btnCrearVehiculos;
    private JButton btnCrearHerramientas;
    private JButton btnVovler;
    private JPanel panel1;

    public CrearAlquilablesForm() {
        initComponents();

        btnVovler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.volverAlFormAnterior();
            }
        });
        btnCrearCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //NavegacionForms.agregarForm();
            }
        });
        btnCrearVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.agregarForm(new GestionVehiculoForm());
            }
        });
        btnCrearHerramientas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.agregarForm(new GestionHerramientaForm());
            }
        });
    }

    private void initComponents(){
        setTitle("Form de Crear Alquilables");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);


    }
}
