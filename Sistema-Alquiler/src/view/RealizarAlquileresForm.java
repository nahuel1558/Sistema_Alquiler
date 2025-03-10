package view;

import controller.AlquilerController;
import controller.alquilable.AlquilableController;
import controller.alquilable.CategoriaAlquilableController;
import controller.alquilablesEspecificos.HerramientaController;
import controller.alquilablesEspecificos.IAlquilableController;
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
    private JList listAlquileresVehiculos;
    private JList listAlquilables;
    private JLabel lblAlquilables;
    private JComboBox cmbxTipoAlquilable;
    private JLabel lblAlquilados;
    private JSpinner spnDiasReserva;
    private JButton btnAlquilar;
    private JButton btnEliminarAlquiler;
    private JButton btnFinalizarAlquilerHerramienta;
    private JComboBox cmbxUsuario;
    private JLabel lblCliente;
    private JButton btnVolver;
    private JList listAlquileresHerramientas;
    private JButton btnFinalizarAlquilerVehiculo;
    private JButton btnElimarAlquilerVehiculo;

    private UsuarioController usuarioController;
    private CategoriaAlquilableController categoriaAlquilableController;
    private AlquilableController alquilableController;
    private GestionReservaController gestionReservaController;
    private AlquilerController alquilerController;
    private HerramientaController herramientaController;

    public RealizarAlquileresForm(){

        initComponents();

        alquilerController = new AlquilerController();
        usuarioController = new UsuarioController();
        categoriaAlquilableController = new CategoriaAlquilableController();
        alquilableController = new AlquilableController();
        gestionReservaController = new GestionReservaController();
        herramientaController = new HerramientaController();

        cargarUsuarios();
        cargarCategorias();
        recargarAlquileresEnCurso();


        btnAlquilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAlquialer();
            }
        });

        cmbxTipoAlquilable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long idCategoria = obtenerIdCategoriaCmbx();
                cargarAlquilablesDisponibles(idCategoria);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavegacionForms.volverAlFormAnterior();
            }
        });
        btnFinalizarAlquilerVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarAlquilerVehiculo();
            }
        });
        btnElimarAlquilerVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlquilerVehiculo();
            }
        });
        btnFinalizarAlquilerHerramienta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarAlquilerHerramienta();
            }
        });
        btnEliminarAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlquilerHerramienta();
            }
        });
    }
    private void initComponents(){
        setTitle("Realizar Alquileres");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
    }

    private Long obtenerIdCategoriaCmbx(){
        CategoriaAlquilable categoriaSeleccionada = (CategoriaAlquilable) cmbxTipoAlquilable.getSelectedItem();
        return categoriaSeleccionada.getIdCategoria();
    }

    private void realizarAlquialer(){
        Long idCategoria = obtenerIdCategoriaCmbx();
        AlquilableFactory factory = alquilerController.obtenerFactory(idCategoria);
        IAlquilableController controller = obtenerControladora(idCategoria);
        IAlquilable objetoAlquilar = (IAlquilable) listAlquilables.getSelectedValue();

        IGestionAlquiler alquiler = factory.realizarAlquiler(
                gestionReservaController.crearGestionReserva((Usuario) cmbxUsuario.getSelectedItem(),
                (int) spnDiasReserva.getValue()),
                controller.obtenerById(objetoAlquilar.getId()));
        actualizarCosto(alquiler);
        actualizarEstadoAlquilable(alquiler);
        alquilerController.crearAlquiler(alquiler);
        JOptionPane.showMessageDialog(this.panel1, "Alquiler realizado con éxito.\nCosto: " +
                alquiler.getGestionReserva().getCosto(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        recargarAlquileresEnCurso();
        cargarAlquilablesDisponibles(idCategoria);
    }

    private IAlquilableController obtenerControladora(Long idCategoria) {
        return alquilerController.obtenerControladora(idCategoria);
    }

    private void actualizarEstadoAlquilable(IGestionAlquiler alquiler){
        alquilableController.actualizar(alquiler.getAlquiler().getAlquilable());
    }

    private void actualizarCosto(IGestionAlquiler alquiler){
        gestionReservaController.actualizar(alquiler.getGestionReserva());
    }

    private void recargarAlquileresEnCurso(){
        cargarAlquileresEnCursoVehiculo();
        cargarAlquileresEnCursoHerramienta();
    }

    private void cargarAlquilablesDisponibles(Long idCategoria){
        List<IAlquilable> alquilables = alquilerController.listarAlquilablesDisponibles(idCategoria);
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

    private void cargarAlquileresEnCursoHerramienta(){

        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoHerramientas();
        System.out.println("Número de alquileres en curso: " + alquileres.size());
        DefaultListModel<IGestionAlquiler> modelLista = new DefaultListModel<>();
        for(IGestionAlquiler alquiler : alquileres){
            modelLista.addElement(alquiler);
        }
        listAlquileresVehiculos.setModel(modelLista);
    }

    private void cerrarAlquilerHerramienta(){
        int selectedIndex = listAlquileresHerramientas.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para finalizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoHerramientas();
        IGestionAlquiler alquilerSeleccionado = alquileres.get(selectedIndex);

        alquilerSeleccionado.getGestionReserva().setEstado(false);
        gestionReservaController.actualizar(alquilerSeleccionado.getGestionReserva());

        Alquilable alquilable = alquilerSeleccionado.getAlquiler().getAlquilable();
        alquilable.setDisponible(true);
        alquilableController.actualizar(alquilable);
        recargarAlquileresEnCurso();


        JOptionPane.showMessageDialog(this, "Alquiler finalizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarAlquilerHerramienta(){
        int selectedIndex = listAlquileresHerramientas.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoHerramientas();
        IGestionAlquiler alquilerSeleccionado = alquileres.get(selectedIndex);
        alquilerController.eliminarAlquiler(alquilerSeleccionado.getId());
        cargarAlquileresEnCursoHerramienta();
    }


    // Metodos de Vehiculos

    private void cargarAlquileresEnCursoVehiculo(){

        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoVehiculo();
        System.out.println("Número de alquileres en curso: " + alquileres.size());
        DefaultListModel<IGestionAlquiler> modelLista = new DefaultListModel<>();
        for(IGestionAlquiler alquiler : alquileres){
            modelLista.addElement(alquiler);
        }
        listAlquileresVehiculos.setModel(modelLista);
    }

    private void cerrarAlquilerVehiculo(){
        int selectedIndex = listAlquileresVehiculos.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para finalizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoVehiculo();
        IGestionAlquiler alquilerSeleccionado = alquileres.get(selectedIndex);
        alquilerSeleccionado.getGestionReserva().setEstado(false);
        gestionReservaController.actualizar(alquilerSeleccionado.getGestionReserva());
        Alquilable alquilable = alquilerSeleccionado.getAlquiler().getAlquilable();
        alquilable.setDisponible(true);
        alquilableController.actualizar(alquilable);
        recargarAlquileresEnCurso();
        JOptionPane.showMessageDialog(this, "Alquiler finalizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarAlquilerVehiculo(){
        int selectedIndex = listAlquileresVehiculos.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un alquiler para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<IGestionAlquiler> alquileres = alquilerController.listarAlquileresEnCursoVehiculo();
        IGestionAlquiler alquilerSeleccionado = alquileres.get(selectedIndex);
        alquilerController.eliminarAlquiler(alquilerSeleccionado.getId());
        cargarAlquileresEnCursoVehiculo();
    }
}
