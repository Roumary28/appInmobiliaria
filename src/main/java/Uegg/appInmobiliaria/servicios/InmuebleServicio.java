package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Imagen;
import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.enums.Tipo;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import Uegg.appInmobiliaria.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearInmueble(MultipartFile archivo, String idUsuarioEnte, Tipo tipo, String ubicacion, Double superficie, Integer ambientes, String descripcion, Double precioVenta, Double precioAlquiler,
            String tipoOferta) throws MyException {

        validar(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuarioEnte);
        Inmueble inmueble = new Inmueble();
        Usuario usuarioEnte = respuesta.get();
        inmueble.setUsuarioEnte(usuarioEnte);
        inmueble.setTipo(tipo);
        inmueble.setUbicacion(ubicacion);
        inmueble.setSuperficie(superficie);
        inmueble.setAmbientes(ambientes);
        inmueble.setDescripcion(descripcion);
        inmueble.setPrecioVenta(precioVenta);
        inmueble.setPrecioAlquiler(precioAlquiler);
        inmueble.setDisponibildad(true);
        inmueble.setTipoOferta(tipoOferta);
        Imagen imagen = imagenServicio.guardar(archivo);
        inmueble.setImagen(imagen);
        inmueble.setFechaAlta(new Date());
        inmuebleRepositorio.save(inmueble);

    }

    @Transactional
    public void modificarInmueble(String id, Tipo tipo, String ubicacion, Double superficie, Integer ambientes, String descripcion, Double precioVenta, Double precioAlquiler,
            String tipoOferta) throws MyException {

        validar(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Inmueble inmueble = respuesta.get();
            inmueble.setTipo(tipo);
            inmueble.setUbicacion(ubicacion);
            inmueble.setSuperficie(superficie);
            inmueble.setAmbientes(ambientes);
            inmueble.setDescripcion(descripcion);
            inmueble.setPrecioVenta(precioVenta);
            inmueble.setPrecioAlquiler(precioAlquiler);
            inmueble.setTipoOferta(tipoOferta);

            inmuebleRepositorio.save(inmueble);
        }

    }

    public void NoDisponible(String id) {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Inmueble inmueble = respuesta.get();

            inmueble.setDisponibildad(false);
            inmuebleRepositorio.save(inmueble);
        }

    }

    public void Disponible(String id) {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Inmueble inmueble = respuesta.get();

            inmueble.setDisponibildad(true);
            inmuebleRepositorio.save(inmueble);
        }

    }

    @Transactional
    public void borrarInmueble(String id) throws MyException {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            inmuebleRepositorio.deleteById(id);

        }
    }

    public Inmueble getOne(String id) {
        return inmuebleRepositorio.getOne(id);
    }

    public List<Inmueble> listarInmuebles() {
        List<Inmueble> inmuebles = inmuebleRepositorio.findAll();
        return inmuebles;
    }

    // los siguientes 2 metodos son para probar los query del repositorio
    public List<Inmueble> listarTipoInmueble(Tipo tipo, String tipoOferta) {

        List<Inmueble> tipoInmueble = inmuebleRepositorio.buscarPorTipo(tipo, tipoOferta);
        return tipoInmueble;
    }

    public List<Inmueble> listarInmuebleAmbientes(Integer ambientes) {

        List<Inmueble> inmuebleAmbiente = inmuebleRepositorio.buscarPorAmbientes(ambientes);
        return inmuebleAmbiente;
    }

    public void validar(Tipo tipo, String ubicacion, Double superficie, Integer ambientes, String descripcion, Double precioVenta, Double precioAlquiler,
            String tipoOferta) throws MyException {

        if (tipo == null) {
            throw new MyException("El tipo no puede ser nulo");
        }

        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new MyException("La ubicacion no puede ser nula");
        }

        if (superficie == null) {
            throw new MyException("La superficie no puede ser nula");
        }

        if (ambientes == null) {
            throw new MyException("La cantidad de ambientes no puede ser nula");
        }

        if (tipoOferta == null || tipoOferta.isEmpty()) {

            throw new MyException("El tipo de oferta no puede ser nulo");

        }

    }

}
