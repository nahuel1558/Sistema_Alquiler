package controller.reservas;

import dao.GestionReservaDAO;
import model.clases.Reserva;
import model.clases.Usuario;
import model.clasesAlquileres.GestionReserva;

import java.util.List;

public class GestionReservaController {

    private final GestionReservaDAO gestionReservaDAO;
    private ReservaController reservaController;
    public GestionReservaController(){
        this.gestionReservaDAO = GestionReservaDAO.getInstance();
    }

    public GestionReserva crearGestionReserva(Usuario usuario, int diasReserva){
        Reserva reserva = reservaController.crearReserva(diasReserva);
        boolean estado = true;
        gestionReservaDAO.crear(new GestionReserva(usuario, reserva, estado));
    return obtenerUltimaGestionReserva();
    }

    public boolean actualizar(GestionReserva gestionReserva){
        return gestionReservaDAO.actualizar(gestionReserva);
    }

    public List<GestionReserva> listarGestionReservaEnCurso(Usuario usuario){
        return gestionReservaDAO.listarGestionReservaEnCurso(usuario.getIdUsuario());
    }

    private GestionReserva obtenerUltimaGestionReserva(){
        return gestionReservaDAO.obtenerUltimaGestionReserva();
    }
}
