package model.specificClasses;

public abstract class Alquilable {
    private Long idAlquilable;
    private boolean disponible;
    private double tarifaBase;
    private String descripcion;

    public Alquilable(boolean disponible, double tarifaBase, String descripcion) {
        this.disponible = disponible;
        this.tarifaBase = tarifaBase;
        this.descripcion = descripcion;
    }

    public Alquilable(Long idAlquilable, boolean disponible, double tarifaBase, String descripcion) {
        this.idAlquilable = idAlquilable;
        this.disponible = disponible;
        this.tarifaBase = tarifaBase;
        this.descripcion = descripcion;
    }
    public Long getIdAlquilable() {
        return idAlquilable;
    }

    public void setIdAlquilable(Long idAlquilable){
        this.idAlquilable = idAlquilable;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}