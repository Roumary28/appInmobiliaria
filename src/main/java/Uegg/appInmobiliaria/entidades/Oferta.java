package Uegg.appInmobiliaria.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Gimenez Victor
 */
@Entity
public class Oferta {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Double montoOferta;
    @ManyToOne
    private Inmueble inmueble;
    @ManyToOne
    private Usuario usuarioCliente;
    @Temporal(TemporalType.DATE)
    private Date fechaOferta;
    private Boolean vigente;

    public Oferta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMontoOferta() {
        return montoOferta;
    }

    public void setMontoOferta(Double montoOferta) {
        this.montoOferta = montoOferta;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Date getFechaOferta() {
        return fechaOferta;
    }

    public void setFechaOferta(Date fechaOferta) {
        this.fechaOferta = fechaOferta;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }
    
}