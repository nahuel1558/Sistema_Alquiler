package dao;

import config.DataBaseConnection;
import model.clases.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements IDAO<Reserva> {

    private static volatile ReservaDAO instance;


    private static final String INSERT_RESERVA_SQL = "INSERT INTO reservas(dias_reservado, fecha_inicio, fecha_fin, id_estado_reserva) VALUE(?,?,?,?);";
    private static final String SELECT_RESERVA_BY_ID_SQL = "SELECT r.*, e.* FROM reservas r " +
            "JOIN estados_reserva e ON r.id_estado_reserva = e.id WHERE r.id = ?;";
    private static final String SELECT_RESERVA_ALL_SQL = "SELECT r.*, e.* FROM reservas r " +
            "JOIN estados_reserva e ON r.id_estado_reserva = e.id";
    private static final String UPDATE_RESERVA_SQL = "UPDATE reservas SET dias_reservado=?, fecha_inicio=?, fecha_fin=? WHERE id=?;";
    private static final String DELETE_RESERVA_BY_ID_SQL = "DELETE FROM reservas WHERE id=?;";

    private ReservaDAO(){}

    public static ReservaDAO getInstance(){
        if(instance == null){
            synchronized (ReservaDAO.class){
                if(instance == null){
                    instance = new ReservaDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(Reserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_RESERVA_SQL)) {

            statement.setInt(1, object.getDiasReservado());
            statement.setDate(2, (Date) object.getFechaInicio());
            statement.setDate(3,(Date) object.getFechaFin());
            statement.setLong(4,1L);

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la reserva", e);
        }
    }

    @Override
    public boolean actualizar(Reserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVA_SQL)){

            statement.setInt(1, object.getDiasReservado());
            statement.setDate(2, (Date) object.getFechaInicio());
            statement.setDate(3,(Date) object.getFechaFin());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reserva> listar() {
        List<Reserva> reservaList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_RESERVA_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Reserva reserva = mapReserva(resultSet);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    @Override
    public Reserva obtenerPorId(Long id) {
        Reserva reserva = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_RESERVA_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = mapReserva(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la reserva por ID", e);
        }

        return reserva;
    }

    @Override
    public boolean eliminarPorId(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RESERVA_BY_ID_SQL)) {
            statement.setLong(1, id);
            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva por ID", e);
        }
    }

    private Reserva mapReserva(ResultSet resultSet) throws SQLException {
        Reserva reserva = new Reserva();

        reserva.setIdReserva(resultSet.getLong("id"));
        reserva.setDiasReservado(resultSet.getInt("dias_reservado"));
        reserva.setFechaInicio(resultSet.getDate("fecha_inicio"));
        reserva.setFechaFin(resultSet.getDate("fecha_fin"));

        return reserva;
    }

}
