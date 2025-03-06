package controller.reservas;

import dao.ReservaDAO;
import model.clases.Reserva;

public class ReservaController {

    private final ReservaDAO reservaDAO;

    public ReservaController(){
        this.reservaDAO = ReservaDAO.getInstance();
    }

    public Reserva crearReserva(int diasReserva){
        Reserva reserva = Reserva.nuevaReserva(diasReserva);
        reservaDAO.crear(reserva);
        return obtenerUltimaReserva();
    }

    private Reserva obtenerUltimaReserva(){
        return reservaDAO.obtenerUltimaReserva();
    }
}
