package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Oferta;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.repositorios.OfertaRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gimenez Victor
 */
@Service
public class OfertaServicio {

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    public void crear(Double monto, Inmueble inmueble, Usuario usuarioCliente) {
        Oferta oferta = new Oferta();
        oferta.setMontoOferta(monto);
        oferta.setInmueble(inmueble);
       oferta.setUsuarioCliente(usuarioCliente);
        oferta.setFechaOferta(new Date());
        oferta.setVigente(true);
        ofertaRepositorio.save(oferta);
    }

    public void enviarOferta(){
        
    }
    
}
