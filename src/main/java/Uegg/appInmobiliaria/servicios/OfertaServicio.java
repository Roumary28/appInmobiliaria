package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Oferta;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import Uegg.appInmobiliaria.repositorios.OfertaRepositorio;
import Uegg.appInmobiliaria.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfertaServicio {

    @Autowired
    private OfertaRepositorio ofertaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    private InmuebleServicio inmuebleServicio;

    @Transactional
    public void crearOfertaCliente(Double monto, String idInmueble, String idCliente) throws MyException {
        validar(monto);
        Inmueble inmueble = inmuebleRepositorio.getOne(idInmueble);
        Usuario usuario = usuarioRepositorio.getOne(idCliente);
        Oferta oferta = new Oferta();
        oferta.setUsuarioCliente(usuario);
        oferta.setMontoOferta(monto);
        oferta.setInmueble(inmueble);
        oferta.setFechaOferta(new Date());
        oferta.setEstadoOferta("PENDIENTE");
        oferta.setVigente(true);
        ofertaRepositorio.save(oferta);
    }
    
    @Transactional
    public void eliminarOferta (String id) {
        Oferta oferta = ofertaRepositorio.getOne(id);
        
        ofertaRepositorio.delete(oferta);
    }

    @Transactional
    public void aceptarOferta(String idOferta) {
        Oferta oferta = ofertaRepositorio.getOne(idOferta);
        oferta.setEstadoOferta("ACEPTADA");
        oferta.setVigente(false);
        Inmueble inmueble = oferta.getInmueble();
        inmueble.setDisponibildad(false);
        inmuebleRepositorio.save(inmueble);
        ofertaRepositorio.save(oferta);
        rechazarOfertas(inmueble.getId());
    }

    @Transactional
    public void confirmarOferta(String id){
        Oferta oferta = ofertaRepositorio.getOne(id);
        Inmueble inmueble = oferta.getInmueble();
        if(oferta.getEstadoOferta().equalsIgnoreCase("ACEPTADA")){
            inmueble.setUsuarioPropietario(oferta.getUsuarioCliente());
            oferta.setEstadoOferta("CONFIRMADA");
            ofertaRepositorio.save(oferta);
            inmuebleRepositorio.save(inmueble);
        }
    }
    
    @Transactional
    public void descartarOferta(String id){
        Oferta oferta = ofertaRepositorio.getOne(id);
        if(oferta.getEstadoOferta().equalsIgnoreCase("ACEPTADA")){
            oferta.setEstadoOferta("DESCARTADA");
            ofertaRepositorio.save(oferta);
        }
    }
    //Falta descantar inmueble del ente
    /*
    @Transactional
    public void transaccionCompra(String idOferta){
        Optional<Oferta> respuesta = ofertaRepositorio.findById(idOferta);
        if(respuesta.isPresent()){
            Oferta oferta = new Oferta();
            oferta.setEstadoOferta("CONFIRMADA");
            Usuario usuario = usuarioRepositorio.getOne(oferta.getUsuarioCliente().getId());
            Usuario usuarioEnte = usuarioRepositorio.getOne(oferta.getInmueble().getUsuarioPropietario().getId());
            usuario.getInmuebles().add(oferta.getInmueble());
            
        }
    }
    */
    @Transactional
    public void rechazarOferta(String idOferta) {
        Optional<Oferta> respuesta = ofertaRepositorio.findById(idOferta);
        if (respuesta.isPresent()) {
            Oferta oferta = new Oferta();
            oferta.setEstadoOferta("RECHAZADA");
            oferta.setVigente(false);
            ofertaRepositorio.save(oferta);
        }
    }

    @Transactional
    private void rechazarOfertas(String idInmueble) {
        List<Oferta> ofertas = ofertaRepositorio.buscarPorInmueble(idInmueble);
        for (Oferta oferta : ofertas) {
            if (oferta.getEstadoOferta().equalsIgnoreCase("PENDIENTE")) {
                oferta.setEstadoOferta("RECHAZADA");
                oferta.setVigente(false);
                ofertaRepositorio.save(oferta);
            }
        }
    }

    
    public List<Oferta> listarOfertaCliente(String id) {
        return ofertaRepositorio.buscarPorCliente(id);
    }

    
    public List<Oferta> listarOfertasInmueble(String id) {
        return ofertaRepositorio.buscarPorInmueble(id);
    }

    
    public Oferta mejorOferta(String id) {
        return ofertaRepositorio.buscarOfertaMayor(id);
    }

    
    public Oferta contarOfertas(String id) {
        return ofertaRepositorio.contarOfertasPorInmueble(id);
    }

    public Oferta getOne(String idOferta) {
        return ofertaRepositorio.getOne(idOferta);
    }

    public void validar(Double montoOferta) throws MyException {
        if (montoOferta <= 0 || montoOferta == null) {
            throw new MyException("La oferta no puede ser vacia o  se rmenor a cero.");
        }
    }
}
