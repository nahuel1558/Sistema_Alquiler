package model.clases;

public class EstadoReserva {
    private Long idEstadoReserva;
    private String estadoReserva;

    public EstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public EstadoReserva(Long idEstadoReserva, String estadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
        this.estadoReserva = estadoReserva;
    }

    public Long getIdEstadoReserva() {
        return idEstadoReserva;
    }

    public void setIdEstadoReserva(Long idEstadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}
