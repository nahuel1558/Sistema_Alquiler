package controller;

import controller.alquilable.AlquilableController;
import controller.reservas.GestionReservaController;
import dao.AlquilerDAO;
import model.clasesAlquileres.IGestionAlquiler;

import java.util.List;

public class AlquilerController {

    private final AlquilerDAO alquilerDAO;

    public AlquilerController(){
        this.alquilerDAO = AlquilerDAO.getInstance();
    }


    public List<IGestionAlquiler> listarAlquileresEnCurso(){
        return alquilerDAO.listarAlquileresEnCurso();
    }

}
