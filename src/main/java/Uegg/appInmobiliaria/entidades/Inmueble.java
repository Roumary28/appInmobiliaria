package Uegg.appInmobiliaria.entidades;

import Uegg.appInmobiliaria.enums.Tipo;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Inmueble {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String ubicacion;
    private Double superficie;
    private Integer ambientes;
    private String descripcion;
    private Double precioVenta;
    private Double precioAlquiler;
    private Boolean disponibildad;
    private String tipoOferta;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "inmueble")
    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ente_id")
    private Usuario usuarioEnte;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inmueble", cascade = CascadeType.ALL)
    private List<Oferta> ofertas;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    public Inmueble() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public Integer getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(Integer ambientes) {
        this.ambientes = ambientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public Boolean getDisponibildad() {
        return disponibildad;
    }

    public void setDisponibildad(Boolean disponibildad) {
        this.disponibildad = disponibildad;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Usuario getUsuarioEnte() {
        return usuarioEnte;
    }

    public void setUsuarioEnte(Usuario usuarioEnte) {
        this.usuarioEnte = usuarioEnte;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
