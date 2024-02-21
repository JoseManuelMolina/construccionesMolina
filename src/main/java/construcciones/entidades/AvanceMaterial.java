package construcciones.entidades;

import javax.persistence.*;

@Entity
@Table(name = "avance_material")
public class AvanceMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_avance", foreignKey = @ForeignKey(name = "fk_avance_avancematerial"))
    private Avance avance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_material", foreignKey = @ForeignKey(name = "fk_material_avancematerial"))
    private Material material;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    public AvanceMaterial(Long id, Avance avance, Material material, Long cantidad) {
        this.id = id;
        this.avance = avance;
        this.material = material;
        this.cantidad = cantidad;
    }

    public AvanceMaterial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avance getAvance() {
        return avance;
    }

    public void setAvance(Avance avance) {
        this.avance = avance;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "AvanceMaterial{" +
                "id=" + id +
                ", avance=" + avance +
                ", material=" + material +
                ", cantidad=" + cantidad +
                '}';
    }
}
