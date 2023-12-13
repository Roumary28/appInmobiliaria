package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.repositorios.ComentarioRepositorio;
import Uegg.appInmobiliaria.servicios.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gimenez Victor
 */
@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {
    @Autowired
    ComentarioServicio comentarioServicio;
    @Autowired
    ComentarioRepositorio comentarioRepositorio;
    
    private String hacerComentario(){
        return "";
    }
    private String enviarComentario(){
        return "";
    }
    private String listarComentarios(){
        return "";
    }
    private String listarComentarioInmueble(){
        return "";
    }
    private String listarComentarioUsuario(){
        return "";
    }
    
    private String borrarComentario(){
        return "";
    }
}
