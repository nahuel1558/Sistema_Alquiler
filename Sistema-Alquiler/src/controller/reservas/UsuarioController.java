package controller.reservas;

import dao.UsuarioDAO;
import model.clases.Usuario;

import java.util.List;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;

    public UsuarioController(){
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public List<Usuario> traerLista(){
        return usuarioDAO.listar();
    }
}
