package view;

import controller.reservas.UsuarioController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RealizarAlquileresForm {
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDNI;
    private JTextField txtEmail;
    private JTextField txtCelular;
    private JLabel lblTituloRealizarAlquileres;
    private JList listAlquileres;
    private JList listAlquilables;
    private JLabel lblAlquilables;
    private JComboBox cmbxTipoAlquilable;
    private JLabel lblAlquilados;
    private JSpinner spinner1;
    private JButton btnAlquilar;
    private JButton bntCancelarReserva;
    private JButton btnFinalizarAlquiler;

    private UsuarioController usuarioController;

    public RealizarAlquileresForm(){
        usuarioController = new UsuarioController();


        btnAlquilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void verificarUsuario(){
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String email = txtEmail.getText().trim();
        String celular = txtCelular.getText().trim();
    }
}
