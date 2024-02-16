package construcciones.dto;

import construcciones.entidades.Cliente;

import java.sql.Date;

public class ProyectoCompletoDTO {

    private Long id;
    private String nombre;
    private Double presupuesto;
    private Date fechaInicio;
    private String plano;
    private String categoria;
    private Cliente cliente;

    public ProyectoCompletoDTO(Long id, String nombre, Double presupuesto, Date fechaInicio, String plano, String categoria, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.fechaInicio = fechaInicio;
        this.plano = plano;
        this.categoria = categoria;
        this.cliente = cliente;
    }

    public ProyectoCompletoDTO() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ProyectoCompletoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", fechaInicio=" + fechaInicio +
                ", plano='" + plano + '\'' +
                ", categoria='" + categoria + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
