package construcciones.entidades;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Clientes")
public class Cliente implements Serializable {

//    Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="cif", length= 9, unique = true, nullable = false)
    private String cif;

    @Column(name="razon_social", length = 100, unique = true, nullable = false)
    private String razonSocial;

    @Column(name="email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name="persona_contacto", length = 100)
    private String personaContacto;

    @Column(name = "direccion", length = 200)
    private String direccion;

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
