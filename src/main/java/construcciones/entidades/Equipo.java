//package construcciones.entidades;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@Table(name = "Equipos")
//public class Equipo implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "nombre", length = 100, nullable = false)
//    private String nombre;
//
//    @ManyToMany(mappedBy = "equipos")
//    private List<Avance> avances;
//
//    public Equipo(Long id, String nombre) {
//        this.id = id;
//        this.nombre = nombre;
//        this.avances = new ArrayList<>();
//    }
//
//    public Equipo() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public List<Avance> getAvances() {
//        return avances;
//    }
//
//    public void setAvances(List<Avance> avances) {
//        this.avances = avances;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Equipo equipo = (Equipo) o;
//        return Objects.equals(id, equipo.id) && Objects.equals(nombre, equipo.nombre);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nombre);
//    }
//
//    @Override
//    public String toString() {
//        return "Equipo{" +
//                "id=" + id +
//                ", nombre='" + nombre + '\'' +
//                '}';
//    }
//}
