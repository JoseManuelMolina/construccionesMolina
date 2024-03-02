package construcciones.entidades;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Avances")
public class Avance implements Serializable {

//    Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column(name = "fecha", nullable = false)
    @Expose
    private Date fecha;

    @Column(name = "descripcion", length = 255,nullable = false)
    @Expose
    private String descripcion;

    @Column(name = "porcentaje_completado", nullable = false)
    @Expose
    private int porcentajeCompletado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto",foreignKey = @ForeignKey(name = "fk_avance_proyecto"))
    @Expose
    private Proyecto proyecto;

    @OneToMany(mappedBy = "avance", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AvanceMaterial> avanceMateriales = new ArrayList<>();


    public Avance(Long id, Date fecha, String descripcion, int porcentajeCompletado, Proyecto proyecto) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.porcentajeCompletado = porcentajeCompletado;
        this.proyecto = proyecto;
    }

    public Avance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcentajeCompletado() {
        return porcentajeCompletado;
    }

    public void setPorcentajeCompletado(int porcentajeCompletado) {
        this.porcentajeCompletado = porcentajeCompletado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<AvanceMaterial> getAvanceMateriales() {
        return avanceMateriales;
    }

    public void setAvanceMateriales(List<AvanceMaterial> avanceMateriales) {
        this.avanceMateriales = avanceMateriales;
    }

    @Override
    public String toString() {
        return "Avance{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", porcentajeCompletado=" + porcentajeCompletado +
                ", proyecto=" + proyecto +
                '}';
    }
}

