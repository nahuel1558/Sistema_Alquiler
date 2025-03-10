
package dao;

import config.DataBaseConnection;
import model.clasesAlquileres.AlquilerHerramienta;
import model.clasesAlquileres.AlquilerVehiculo;
import model.clasesAlquileres.IGestionAlquiler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO implements IDAO<IGestionAlquiler> {

    private static volatile AlquilerDAO instance;

    private static final String INSERT_SQL = "INSERT INTO alquileres(id_gestion_reserva, id_alquilable, id_categoria_alquilable) VALUE(?,?,?);";
    private static final String UPDATE_SQL = "UPDATE alquileres SET id_gestion_reserva=?, id_alquilable=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM alquileres";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM alquileres ";
    private static final String DELETE_SQL = "DELETE FROM alquileres WHERE id= ?";

    private AlquilerDAO(){}

    public static AlquilerDAO getInstance(){
        if(instance == null){
            synchronized (AlquilerDAO.class){
                if(instance == null){
                    instance = new AlquilerDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(IGestionAlquiler object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getGestionReserva().getIdGestionReserva());
            statement.setLong(2, object.getAlquiler().getAlquilable().getIdAlquilable());
            statement.setLong(3,object.getAlquiler().getAlquilable().getCategoria().getIdCategoria());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }    }

    @Override
    public boolean actualizar(IGestionAlquiler object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setLong(1, object.getGestionReserva().getIdGestionReserva());
            statement.setLong(2, object.getAlquiler().getAlquilable().getIdAlquilable());
            statement.setString(3, object.getAlquiler().getAlquilable().getCategoria().getNombreCategoria());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<IGestionAlquiler> listar() {
        List<IGestionAlquiler> iGestionAlquilerList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                IGestionAlquiler gestionAlquiler = mapGestionAlquiler(resultSet);
                iGestionAlquilerList.add(gestionAlquiler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iGestionAlquilerList;
    }

    @Override
    public IGestionAlquiler obtenerPorId(Long id) {
        IGestionAlquiler iGestionAlquiler = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    iGestionAlquiler = mapGestionAlquiler(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return iGestionAlquiler;
    }

    @Override
    public boolean eliminarPorId(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar por ID", e);
        }
    }


    public List<IGestionAlquiler> listarAlquileresActivosVehiculos(){
        List<IGestionAlquiler> alquileres = new ArrayList<>();

        String sql = "SELECT a.* FROM alquileres a " +
                "JOIN gestion_reservas g ON g.id = a.id_gestion_reserva " +
                "JOIN alquilables al ON al.id = a.id_alquilable " +
                "WHERE g.estado = 1 AND a.id_categoria_alquilable = 1";
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    IGestionAlquiler alquiler = mapGestionAlquilerVehiculo(resultSet);
                    alquileres.add(alquiler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alquileres;
    }

    public List<IGestionAlquiler> listarAlquileresActivosHerramientas(){
        List<IGestionAlquiler> alquileres = new ArrayList<>();

        String sql = "SELECT a.* FROM alquileres a " +
                "JOIN gestion_reservas g ON g.id = a.id_gestion_reserva " +
                "JOIN alquilables al ON al.id = a.id_alquilable " +
                "WHERE g.estado = 1 AND a.id_categoria_alquilable = 2";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                IGestionAlquiler alquiler = mapGestionAlquilerHerramienta(resultSet);
                alquileres.add(alquiler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alquileres;
    }

    private IGestionAlquiler mapGestionAlquilerVehiculo(ResultSet resultSet) throws SQLException {
        IGestionAlquiler iGestionAlquiler = new AlquilerVehiculo();

        iGestionAlquiler.setId(resultSet.getLong("id"));
        iGestionAlquiler.setGestionReserva(GestionReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_gestion_reserva")));
        iGestionAlquiler.setAlquiler(VehiculoDAO.getInstance().obtenerByIdAlquilable(resultSet.getLong("id_alquilable")));

        return iGestionAlquiler;
    }

    private IGestionAlquiler mapGestionAlquilerHerramienta(ResultSet resultSet) throws SQLException {
        IGestionAlquiler iGestionAlquiler = new AlquilerHerramienta();
        iGestionAlquiler.setId(resultSet.getLong("id"));
        iGestionAlquiler.setGestionReserva(GestionReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_gestion_reserva")));
        iGestionAlquiler.setAlquiler(HerramientaDAO.getInstance().obtenerByIdAlquilable(resultSet.getLong("id_alquilable")));
return iGestionAlquiler;
    }

    private IGestionAlquiler mapGestionAlquiler(ResultSet resultSet) throws SQLException {
        IGestionAlquiler iGestionAlquiler = null;

        iGestionAlquiler.setId(resultSet.getLong("id"));
        iGestionAlquiler.setGestionReserva(GestionReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_gestion_reserva")));
        iGestionAlquiler.getAlquiler().setAlquilable(AlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_alquilable")));
        return iGestionAlquiler;
    }

}
