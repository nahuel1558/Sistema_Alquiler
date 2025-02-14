package model.clases;

import model.strategies.IEstrategiaCosto;

public class TipoAlquilable {

    private Long idTipoAlquilable;
    private String nombreTipo;
    private double tarifaBase;
    private IEstrategiaCosto estrategiaCosto;

    public TipoAlquilable(String nombreTipo, double tarifaBase, IEstrategiaCosto estrategiaCosto) {
        this.nombreTipo = nombreTipo;
        this.tarifaBase = tarifaBase;
        this.estrategiaCosto = estrategiaCosto;
    }

    public TipoAlquilable(Long idTipoAlquilable, String nombreTipo, double tarifaBase, IEstrategiaCosto estrategiaCosto) {
        this.idTipoAlquilable = idTipoAlquilable;
        this.nombreTipo = nombreTipo;
        this.tarifaBase = tarifaBase;
        this.estrategiaCosto = estrategiaCosto;
    }

    public Long getIdTipoAlquilable() {
        return idTipoAlquilable;
    }

    public void setIdTipoAlquilable(Long idTipoAlquilable) {
        this.idTipoAlquilable = idTipoAlquilable;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public IEstrategiaCosto getEstrategiaCosto() {
        return estrategiaCosto;
    }

    public void setEstrategiaCosto(IEstrategiaCosto estrategiaCosto) {
        this.estrategiaCosto = estrategiaCosto;
    }
}
