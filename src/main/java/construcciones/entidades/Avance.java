package construcciones.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Avances")
public class Avance implements Serializable {

//    Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descripcion", length = 255,nullable = false)
    private String descripcion;

    @Column(name = "porcentaje_completado", nullable = false)
    private int porcentajeCompletado;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

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

