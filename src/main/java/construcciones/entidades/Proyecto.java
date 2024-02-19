package construcciones.entidades;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Proyectos")
public class Proyecto implements Serializable {

//    Atributos

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @Column(name = "nombre", length = 100 ,unique = true)
    private String nombre;

    @Expose
    @Column(name = "presupuesto", nullable = false)
    private Double presupuesto;

    @Expose
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Expose
    @Column(name = "plano", nullable = true)
    private String plano;

    @Expose
    @Column(name = "categoria", length = 100, nullable = true)
    private String categoria;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_proyecto_cliente"))
    @Expose
    private Cliente cliente;


    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Avance> avances = new ArrayList<>();

    public void agregarAvance(Avance avance){
        avances.add(avance);
        avance.setProyecto(this);
    }

    public void borrarAvance(Avance avance){
        avances.remove(avance);
        avance.setProyecto(null);
    }

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

    public List<Avance> getAvances() {
        return avances;
    }

    public void setAvances(List<Avance> avances) {
        this.avances = avances;
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
                ", cliente=" + cliente.getId() + "\n"+
                "}\n";
    }
}


