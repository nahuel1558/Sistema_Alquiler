package view.alquilables;

import controller.alquilablesEspecificos.VehiculoController;
import model.clases.Vehiculo;
import view.NavegacionForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionVehiculoForm extends JFrame{
    private JTextField txtNombreTipo;
    private JTextField txtTarifaBase;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtDescripcion;
    private JCheckBox checkDisponible;
    private JList listVehiculos;
    private JButton btnCrearVehiculo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JPanel jpanel1;
    private JButton btnVolver;


    private VehiculoController vehiculoController;

    public GestionVehiculoForm(){
        initComponents();
        vehiculoController = new VehiculoController();
        cargarVehiculos();
        btnActualizar.setEnabled(false);
        btnEditar.setEnabled(false);

        btnCrearVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVehiculo();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVehiculo();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVehiculo();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVehiculo();
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.volverAlFormAnterior();
            }
        });
    }

    private void initComponents() {
        setTitle("Gestión de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(jpanel1);
    }
    private void crearVehiculo() {
        Long idCategoria = 1L;
        String nombreTipo = txtNombreTipo.getText();
        double tarifaBase = Double.parseDouble(txtTarifaBase.getText());
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String descripcion = txtDescripcion.getText();
        boolean disponible = checkDisponible.isSelected();
        vehiculoController.crearVehiculo(idCategoria, nombreTipo, tarifaBase, disponible, descripcion, marca, modelo);
        cargarVehiculos();
    }

    private void editarVehiculo(){
    }

    private void actualizarVehiculo(){

        JOptionPane.showMessageDialog(this, "No se puede actualizar de momento.", "Información", JOptionPane.INFORMATION_MESSAGE);

    }

    private void cargarVehiculos(){
        List<Vehiculo> vehiculos = vehiculoController.listar();
        DefaultListModel<Vehiculo> modelLista = new DefaultListModel<>();
        for(Vehiculo vehiculo : vehiculos){
            modelLista.addElement(vehiculo);
        }
        listVehiculos.setModel(modelLista);
    }

    private void eliminarVehiculo(){
        Vehiculo vehiculoSeleccionado = (Vehiculo) listVehiculos.getSelectedValue();
        if (vehiculoSeleccionado != null) {
            vehiculoController.eliminarPorId(vehiculoSeleccionado.getId());
            cargarVehiculos();
        }
    }

}
