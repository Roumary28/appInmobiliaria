package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Comentario;
import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.repositorios.ComentarioRepositorio;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import Uegg.appInmobiliaria.repositorios.UsuarioRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gimenez Victor
 */
@Service
public class ComentarioServicio {

     @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    
    public void crearComentario(String idInmueble, String idUsuario, String contenido){
        Inmueble inmueble = inmuebleRepositorio.getOne(idInmueble);
        Usuario usuario = usuarioRepositorio.getOne(idUsuario);
        Comentario comentario = new Comentario();
        comentario.setInmueble(inmueble);
        comentario.setUsuario(usuario);
        comentario.setContenido(contenido);
        comentario.setFecha(new Date());
        comentario.setEstado(Boolean.TRUE);
        comentarioRepositorio.save(comentario);
    }
    
    public void modificarComentario(){
        
    }
   
    public void borrarComentario(){
        
    }
    
     public void bajaComentario(){
        
    }
    
    public void bajaAutomatica(){
        
    }
}
