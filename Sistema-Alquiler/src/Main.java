import model.clases.*;
import model.factories.factoryMethod.AlquilableFactory;
import model.factories.factoryMethod.VehiculoFactory;
import model.strategies.IEstrategiaCosto;
import model.strategies.vehiclesStrategy.AutoEstrategia;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        AlquilableFactory factory = new VehiculoFactory();

        Usuario usuario = new Usuario("Juan", "Pérez", 12345678L, "juan@example.com", 123456789L);
        CategoriaAlquilable categoria = new CategoriaAlquilable("vehiculo");
        //IEstrategiaCosto estrategiaCosto = new AutoEstrategia();
        TipoAlquilable tipoVehiculo = new TipoAlquilable();
        tipoVehiculo.setNombreTipo("Auto");
        tipoVehiculo.setTarifaBase(100.00);
        tipoVehiculo.setEstrategiaCosto(new AutoEstrategia());

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCategoria(categoria);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setDisponible(true);

        Reserva<Vehiculo> reserva = new Reserva<>(vehiculo, usuario, new Date(), new Date(), 5);

        factory.datos(reserva);
    }
}