package model.specificClasses;

public abstract class Alquilable {
    private Long idAlquilable;
    private boolean disponible;

    public Alquilable(boolean disponible) {
        this.disponible = disponible;
    }

    public Alquilable(Long idAlquilable, boolean disponible) {
        this.idAlquilable = idAlquilable;
        this.disponible = disponible;
    }

    public Long getIdAlquilable() {
        return idAlquilable;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}