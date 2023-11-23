package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.enums.Tipo;
import Uegg.appInmobiliaria.excepciones.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class inmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    @Transactional
    public void crearInmueble(Tipo tipo, String ubicacion, Double superficie, Integer ambientes, String descripcion, Double precioVenta, Double precioAlquiler,
            String tipoOferta) throws MyException {

        validar(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);

        Inmueble inmueble = new Inmueble();

        inmueble.setTipo(tipo);
        inmueble.setUbicacion(ubicacion);
        inmueble.setSuperficie(superficie);
        inmueble.setAmbientes(ambientes);
        inmueble.setDescripcion(descripcion);
        inmueble.setPrecioVenta(precioVenta);
        inmueble.setPrecioAlquiler(precioAlquiler);
        inmueble.setDisponibildad(true);
        inmueble.setTipoOferta(tipoOferta);
        inmueble.setFechaAlta(new Date());
        //    agregar   Ente
        inmuebleRepositorio.save(inmueble);

    }

    @Transactional
    public void modificarInmueble(Long id, Tipo tipo, String ubicacion, Double superficie, Integer ambientes, String descripcion, Double precioVenta, Double precioAlquiler,
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

    public void NoDisponible(Long id) {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Inmueble inmueble = respuesta.get();

            inmueble.setDisponibildad(false);
            inmuebleRepositorio.save(inmueble);
        }

    }

    public void Disponible(Long id) {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Inmueble inmueble = respuesta.get();

            inmueble.setDisponibildad(true);
            inmuebleRepositorio.save(inmueble);
        }

    }

    @Transactional
    public void borrarInmueble(Long id) throws MyException {

        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);

        if (respuesta.isPresent()) {

            inmuebleRepositorio.deleteById(id);

        }
    }

    public Inmueble getOne(Long id) {
        return inmuebleRepositorio.getOne(id);
    }

    public List<Inmueble> listarInmuebles() {
        List<Inmueble> inmuebles = inmuebleRepositorio.findAll();
        return inmuebles;
    }

    // los siguientes 2 metodos son para probar los query del repositorio
    public List<Inmueble> listarTipoInmueble(Tipo tipo) {

        List<Inmueble> tipoInmueble = inmuebleRepositorio.buscarPorTipo(tipo);
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

        } else {

            if (tipoOferta.equals("ALQUILER") && precioAlquiler == null) {
                throw new MyException("El precio del alquiler no puede ser nulo");
            } else {

                if (precioVenta == null) {
                    throw new MyException("El precio de venta no puede ser nulo");
                }
            }

        }
    }

}
