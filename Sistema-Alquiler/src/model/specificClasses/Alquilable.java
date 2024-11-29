package model.specificClasses;

public abstract class Alquilable {
    private String idAlquilable; // Identificador único
    private boolean disponible;

    public Alquilable(String idAlquilable, boolean disponible) {
        this.idAlquilable = idAlquilable;
        this.disponible = disponible;
    }

    public String getIdAlquilable() {
        return idAlquilable;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public abstract double calcularCosto(int dias);
}
