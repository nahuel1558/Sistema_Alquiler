package view;

import controller.alquilable.AlquilableController;
import controller.alquilable.CategoriaAlquilableController;
import controller.reservas.GestionReservaController;
import controller.reservas.UsuarioController;
import model.clases.CategoriaAlquilable;
import model.clases.IAlquilable;
import model.clases.Usuario;
import model.clasesAlquileres.IGestionAlquiler;
import model.factories.factoryMethod.AlquilableFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RealizarAlquileresForm extends JFrame {
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
    private JSpinner spnDiasReserva;
    private JButton btnAlquilar;
    private JButton bntCancelarReserva;
    private JButton btnFinalizarAlquiler;
    private JComboBox cmbxUsuario;
    private JLabel lblCliente;

    private UsuarioController usuarioController;
    private CategoriaAlquilableController categoriaAlquilableController;
    private AlquilableController alquilableController;
    private GestionReservaController gestionReservaController;

    public RealizarAlquileresForm(){

        initComponents();

        usuarioController = new UsuarioController();
        categoriaAlquilableController = new CategoriaAlquilableController();
        alquilableController = new AlquilableController();
        gestionReservaController = new GestionReservaController();

        cargarUsuarios();
        cargarCategorias();


        btnAlquilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAlquialer();
            }
        });

        cmbxTipoAlquilable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoriaAlquilable categoriaSeleccionada = (CategoriaAlquilable) cmbxTipoAlquilable.getSelectedItem();
                cargarAlquilablesDisponibles(categoriaSeleccionada);
            }
        });

        btnFinalizarAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarAlquiler();
            }
        });
        cmbxUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarAlquileresEnCurso((Usuario) cmbxUsuario.getSelectedItem());
            }
        });
    }
    private void initComponents(){
        setTitle("Realizar Alquileres");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo este formulario
        setLocationRelativeTo(null);
    }

    private void cerrarAlquilerForm() {
        // Cerrar el formulario actual
        NavegacionForms.volverAlFormAnterior();
    }
    private void realizarAlquialer(){
        CategoriaAlquilable categoriaSeleccionada = (CategoriaAlquilable) cmbxTipoAlquilable.getSelectedItem();
        AlquilableFactory factory = alquilableController.obtenerFactory(categoriaSeleccionada);
        IGestionAlquiler alquiler = factory.realizarAlquiler(
                gestionReservaController.crearGestionReserva((Usuario) cmbxUsuario.getSelectedItem(),
                (int) spnDiasReserva.getValue()),
                (IAlquilable) listAlquilables.getSelectedValue());
        actualizarCosto(alquiler);
        JOptionPane.showMessageDialog(this.panel1, "Alquiler realizado con éxito.\nCosto: " +
                alquiler.getGestionReserva().getCosto(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarCosto(IGestionAlquiler alquiler){
        gestionReservaController.actualizar(alquiler.getGestionReserva());
    }

    private void cerrarAlquiler(){

    }
    private void cargarAlquileresEnCurso(Usuario usuario){

    }
    private void cargarAlquilablesDisponibles(CategoriaAlquilable categoriaAlquilable){
        List<IAlquilable> alquilables = alquilableController.traerListaDisponibles(categoriaAlquilable);
        DefaultListModel<IAlquilable> modelLista= new DefaultListModel<>();
        for(IAlquilable alquilable : alquilables){
            modelLista.addElement(alquilable);
        }
        listAlquilables.setModel(modelLista);
    }

    private void cargarUsuarios(){
        List<Usuario> usuarios = usuarioController.traerLista();
        cmbxUsuario.removeAllItems();
        for(Usuario usuario : usuarios){
            cmbxUsuario.addItem(usuario);
        }
        mostrarUsuarioPersonalizado();
    }

    private void mostrarUsuarioPersonalizado(){
        cmbxUsuario.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Usuario) {
                    Usuario usuario = (Usuario) value;
                    setText(usuario.datosEspecificos());
                }
                return this;
            }
        });
    }


    private void cargarCategorias(){
        List<CategoriaAlquilable> categorias = categoriaAlquilableController.traerLista();
        cmbxTipoAlquilable.removeAllItems();
        for(CategoriaAlquilable categoria : categorias){
            cmbxTipoAlquilable.addItem(categoria);
        }

    }

}
