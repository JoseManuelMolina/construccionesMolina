package construcciones.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="Apikeys")
public class APIKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_key", length = 25, unique = true)
    private String key;

    @Column(name = "usos_disponibles", nullable = false)
    private Long usosDisponibles;

    @Column(name = "usados", nullable = false)
    private Long usados;

    @Column(name = "fecha_creada", nullable = false)
    private Date fechaCreada;

    @Column(name = "activada", nullable = false)
    private Boolean activada;

    public APIKey() {
    }

    public APIKey(String key) {
        this.id = id;
        this.key = key;
        this.usosDisponibles = 50L;
        this.usados = 0L;
        this.fechaCreada = Date.valueOf(LocalDate.now());
        this.activada = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getUsosDisponibles() {
        return usosDisponibles;
    }

    public void setUsosDisponibles(Long usosDisponibles) {
        this.usosDisponibles = usosDisponibles;
    }

    public Long getUsados() {
        return usados;
    }

    public void setUsados(Long usados) {
        this.usados = usados;
    }

    public Date getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Date fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Boolean getActivada() {
        return activada;
    }

    public void setActivada(Boolean activada) {
        this.activada = activada;
    }

    @Override
    public String toString() {
        return "APIKey{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", usosDisponibles=" + usosDisponibles +
                ", usados=" + usados +
                ", fechaCreada=" + fechaCreada +
                ", activada=" + activada +
                '}';
    }
}
