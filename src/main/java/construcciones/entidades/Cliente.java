package construcciones.entidades;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Clientes")
public class Cliente implements Serializable {

//    Atributos
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @Column(name ="cif", length= 9, unique = true, nullable = false)
    private String cif;

    @Expose
    @Column(name="razon_social", length = 100, unique = true, nullable = false)
    private String razonSocial;

    @Expose
    @Column(name="email", length = 100, unique = true, nullable = false)
    private String email;

    @Expose
    @Column(name="persona_contacto", length = 100)
    private String personaContacto;

    @Expose
    @Column(name = "direccion", length = 200)
    private String direccion;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Proyecto> proyectos = new ArrayList<>();

    // Otros atributos y métodos

    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
        proyecto.setCliente(this);
    }

    public void removerProyecto(Proyecto proyecto) {
        proyectos.remove(proyecto);
        proyecto.setCliente(null);
    }

    public Cliente(Long id, String cif, String razonSocial, String email, String personaContacto, String direccion) {
        this.id = id;
        this.cif = cif;
        this.razonSocial = razonSocial;
        this.email = email;
        this.personaContacto = personaContacto;
        this.direccion = direccion;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cif='" + cif + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", email='" + email + '\'' +
                ", personaContacto='" + personaContacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
