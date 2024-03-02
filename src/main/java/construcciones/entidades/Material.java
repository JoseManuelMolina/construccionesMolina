package construcciones.entidades;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Materiales")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column(name = "nombre", length = 200,nullable = false)
    @Expose
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    @Expose
    private int cantidad;

    @Column(name = "coste", nullable = false, precision = 10, scale = 2)
    @Expose
    private BigDecimal coste;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AvanceMaterial> avanceMateriales = new ArrayList<>();

    @ManyToMany(mappedBy = "materiales", fetch = FetchType.LAZY)
    private List<Proveedor> proveedores = new ArrayList<>();

    public Material(Long id, String nombre, int cantidad, BigDecimal coste) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.coste = coste;
    }

    public Material() {
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public List<AvanceMaterial> getAvanceMateriales() {
        return avanceMateriales;
    }

    public void setAvanceMateriales(List<AvanceMaterial> avanceMateriales) {
        this.avanceMateriales = avanceMateriales;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", coste=" + coste +
                '}';
    }
}
