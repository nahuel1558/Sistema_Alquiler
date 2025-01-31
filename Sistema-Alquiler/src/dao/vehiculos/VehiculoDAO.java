package dao.vehiculos;

import config.DataBaseConnection;
import model.specificClasses.vehicleClasses.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO {
    private static volatile VehiculoDAO instance;
    private static final String INSERT_SQL = "INSERT INTO vehiculo(marca, modelo, tarifaBase, disponible) VALUE(?,?,?,?);";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM vehiculos WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM vehiculos";
    private static final String UPDATE_SQL = "UPDATE vehiculos SET marca=?, modelo=?, tarifaBase=?, disponible=?";
    private static final String DELETE_SQL = "DELETE FROM vehiculos WHERE = ?";

    private VehiculoDAO(){}
    public static VehiculoDAO getInstance(){
        if(instance == null){
            synchronized (VehiculoDAO.class){
                if(instance == null){
                    instance = new VehiculoDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException{
        return DataBaseConnection.getConnection();
    }

    public boolean guardarVehiculo(Vehiculo vehiculo) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){

            statement.setString(1, vehiculo.getMarca());
            statement.setString(2, vehiculo.getModelo());
            statement.setDouble(3, vehiculo.getTarifaBase());
            statement.setBoolean(4, vehiculo.isDisponible());

            return statement.executeUpdate() > 0;
        }
    }
/*
    public Vehiculo obtenerVehiculoByID(Integer idVehiculo) throws SQLException{
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)){

            statement.setInt(1, idVehiculo);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new Vehiculo(resultSet.getLong("id"), resultSet.getBoolean("disponible"), resultSet.getString("marca"), resultSet.getString("modelo"), resultSet.getDouble("tarifaBase"));
            }
        }
    }*/
}
