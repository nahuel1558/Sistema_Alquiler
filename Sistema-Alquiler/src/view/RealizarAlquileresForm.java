package view;

import controller.AlquilerController;
import controller.alquilable.AlquilableController;
import controller.alquilable.CategoriaAlquilableController;
import controller.reservas.GestionReservaController;
import controller.reservas.UsuarioController;
import model.clases.Alquilable;
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
    private AlquilerController alquilerController;

    public RealizarAlquileresForm(){

        initComponents();
        alquilerController = new AlquilerController();
        usuarioController = new UsuarioController();
        categoriaAlquilableController = new CategoriaAlquilableController();
        alquilableController = new AlquilableController();
        gestionReservaController = new GestionReservaController();

        cargarUsuarios();
        cargarCategorias();
        cargarAlquileresEnCurso();


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
        int selectedIndex = listAlquileres.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para finalizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCurso();
        IGestionAlquiler alquilerSeleccionado = alquileres.get(selectedIndex);

        alquilerSeleccionado.getGestionReserva().setEstado(false);
        gestionReservaController.actualizar(alquilerSeleccionado.getGestionReserva());

        Alquilable alquilable = alquilerSeleccionado.getAlquiler().getAlquilable();
        alquilable.setDisponible(true);
        alquilableController.actualizar(alquilable);

        cargarAlquileresEnCurso();

        JOptionPane.showMessageDialog(this, "Alquiler finalizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    private void cargarAlquileresEnCurso(){
        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCurso();
        DefaultListModel<String> modelLista = new DefaultListModel<>();
        for(IGestionAlquiler alquiler : alquileres){
            modelLista.addElement(alquiler.toString());
        }
        listAlquileres.setModel(modelLista);
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
