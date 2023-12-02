package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Oferta;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.enums.Rol;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import Uegg.appInmobiliaria.repositorios.OfertaRepositorio;
import Uegg.appInmobiliaria.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gimenez Victor
 */
@Service
public class OfertaServicio {

    @Autowired
    private OfertaRepositorio ofertaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    public void crearOfertaCliente(Double monto, String idInmueble, String idCliente) {
        Optional<Inmueble> respuestaInmueble = inmuebleRepositorio.findById(idInmueble);
        Optional<Usuario> respuestaCliente = usuarioRepositorio.findById(idCliente);

        Oferta oferta = new Oferta();
        Inmueble inmueble = new Inmueble(); //creo un inmueble
        Usuario usuarioC = new Usuario();
        
        if (respuestaInmueble.isPresent()) {
            inmueble = respuestaInmueble.get();
        }
        if (respuestaCliente.isPresent()) {
            usuarioC = respuestaCliente.get();
        }

        oferta.setMontoOferta(monto);
        oferta.setInmueble(inmueble);
        oferta.setUsuarioCliente(usuarioC);
        oferta.setFechaOferta(new Date());
        oferta.setVigente(true);
        ofertaRepositorio.save(oferta);
    }

    public void aceptarOferta(String idOferta) {
        Optional<Oferta> ofertaInmueble = ofertaRepositorio.findById(idOferta);

        if (ofertaInmueble.isPresent()) {
            Oferta oferta = ofertaInmueble.get();
            Usuario usuarioCliente = oferta.getUsuarioCliente();
            if (oferta.getVigente() == true) {
                oferta.setVigente(false);
            }
        }

    }

    public void rechazarOferta() {

    }

    public Oferta getOne(String idOferta) {
        return ofertaRepositorio.getOne(idOferta);
    }
}
