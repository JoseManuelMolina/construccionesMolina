package construcciones.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="Proyectos")
public class Proyecto implements Serializable {

//    Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100 ,unique = true)
    private String nombre;

    @Column(name = "presupuesto", nullable = false)
    private Double presupuesto;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "plano", nullable = true)
    private String plano;

    @Column(name = "categoria", length = 100, nullable = true)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_proyecto_cliente"))
    private Cliente cliente;

    public Proyecto() {
    }

    public Proyecto(Long id, String nombre, Double presupuesto, Date fechaInicio, String plano, String categoria, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.fechaInicio = fechaInicio;
        this.plano = plano;
        this.categoria = categoria;
        this.cliente = cliente;
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
        return "Proyecto{" +
                "id=" + id +
                ", nombre=" + nombre + "\n" +
                ", presupuesto=" + presupuesto + "\n"+
                ", fechaInicio=" + fechaInicio + "\n"+
                ", plano=" + plano + "\n"+
                ", categoria=" + categoria + "\n"+
                ", cliente=" + cliente + "\n"+
                "}\n";
    }
}


