package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Imagen;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepo;
    
    public Imagen guardar(MultipartFile archivo) throws MyException{
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepo.save(imagen);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MyException {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepo.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepo.save(imagen);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
