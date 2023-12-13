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
        comentario.setEstado(true);
        comentarioRepositorio.save(comentario);
    }
    
    public void modificarComentario(String id, String contenido){
        Comentario comentario = comentarioRepositorio.getOne(id);
        comentario.setContenido(contenido);
        comentarioRepositorio.save(comentario);
    }
   
    public void borrarComentario(String id){
        Comentario comentario = comentarioRepositorio.getOne(id);
        comentarioRepositorio.delete(comentario);
    }
    
     public void bajaComentario(String id){
        Comentario comentario = comentarioRepositorio.getOne(id);
        comentario.setEstado(false);
        comentarioRepositorio.save(comentario);
    }
    
     //Trabajar con caducidad por fecha. Si el comentario
     //tiene una antiguedad mayor a 100 dias es dado de baja. 
    public void bajaAutomatica(){
        
    }
}
