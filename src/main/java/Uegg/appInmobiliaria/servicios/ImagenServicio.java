package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Imagen;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.ImagenRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    @Transactional
    public Imagen guardar(MultipartFile archivo) throws MyException {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    @Transactional
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MyException {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen getOne(String id) {
        return imagenRepositorio.getOne(id);
    }

    public List<Imagen> obtenerImagenesPorInmueble(String inmueble_id) {
        return imagenRepositorio.buscarPorIdInmueble(inmueble_id);
    }
    
    @Transactional
    public void eliminar(String id) throws MyException {
        
        Optional<Imagen> respuesta = imagenRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            imagenRepositorio.deleteById(id);
            
        }
    }
    
    @Transactional
    public void eliminarPorInmuebleId(String inmueble_id) throws MyException {
        
        List<Imagen> imagenes = imagenRepositorio.buscarPorIdInmueble(inmueble_id);
        for (Imagen img : imagenes) {
            imagenRepositorio.deleteById(img.getId());
        }
        
    }
}
