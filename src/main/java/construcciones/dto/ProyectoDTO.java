package construcciones.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProyectoDTO implements Serializable {

    @Expose
    private Long id;
    @Expose
    private String nombre;
    @Expose
    private Double presupuesto;
    @Expose
    private Date fechaInicio;
    @Expose
    private String plano;
    @Expose
    private String categoria;
    @Expose
    private Long idCliente;


    public ProyectoDTO(Long id, String nombre, Double presupuesto, Date fechaInicio, String plano, String categoria, Long idCliente) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.fechaInicio = fechaInicio;
        this.plano = plano;
        this.categoria = categoria;
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectoDTO that = (ProyectoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(presupuesto, that.presupuesto) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(plano, that.plano) && Objects.equals(categoria, that.categoria) && Objects.equals(idCliente, that.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, presupuesto, fechaInicio, plano, categoria, idCliente);
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", fechaInicio=" + fechaInicio +
                ", plano='" + plano + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idCliente=" + idCliente +
                '}';
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
