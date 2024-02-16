package construcciones.dto;

public class ProyectoDTO {

    private String nombre;
    private Double presupuesto;

    public ProyectoDTO(String nombre, Double presupuesto) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                '}';
    }
}
