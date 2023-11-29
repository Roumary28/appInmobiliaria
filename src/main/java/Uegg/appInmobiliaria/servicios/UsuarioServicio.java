package Uegg.appInmobiliaria.servicios;

import Uegg.appInmobiliaria.entidades.Imagen;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.enums.Rol;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gimenez Victor
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearCliente(MultipartFile archivo, String denominacion, Long dni, String direccion, Integer codigoPostal, Integer telefono,
            String email, String pass, String pass2) throws MyException {
        validarCliente(denominacion, dni, direccion, email, codigoPostal, telefono, pass, pass2);

        // Crear una instancia de Usuario
        Usuario usuario = new Usuario();
        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);
        usuario.setDenominacion(denominacion);
        usuario.setDni(dni);
        usuario.setDireccion(direccion);
        usuario.setCodigoPostal(codigoPostal);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPass(pass);
        usuario.setRol(Rol.CLIENTE);
        usuario.setFechaAlta(new Date());
        usuario.setActivo(true);
        usuarioRepo.save(usuario);
    }

    @Transactional
    public void crearEnte(MultipartFile archivo, String denominacion, Long cuit, String direccion, Integer codigoPostal, Integer telefono,
            String email, String pass, String pass2) throws MyException {
        validarCliente(denominacion, cuit, direccion, email, codigoPostal, telefono, pass, pass2);

        // Crear una instancia de Usuario
        Usuario usuario = new Usuario();
        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);
        usuario.setDenominacion(denominacion);
        usuario.setCuit(cuit);
        usuario.setDireccion(direccion);
        usuario.setCodigoPostal(codigoPostal);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPass(pass);
        usuario.setRol(Rol.ENTE);
        usuario.setFechaAlta(new Date());
        usuario.setActivo(true);
        usuarioRepo.save(usuario);
    }

    @Transactional
    public void modificarUsuario(
            String id,
            Integer telefono,
            String direccion,
            Integer codigoPostal,
            String email,
            String pass,
            String pass2) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            // Obtener la instancia existente
            Usuario usuario = respuesta.get();
            usuario.setTelefono(telefono);
            usuario.setDireccion(direccion);
            usuario.setCodigoPostal(codigoPostal);

            usuarioRepo.save(usuario);
        }
    }

    @Transactional
    public void modificarRol(String id, String rol) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            // Obtener la instancia existente
            Usuario usuario = respuesta.get();
            usuario.setRol(Rol.valueOf(rol));
            usuarioRepo.save(usuario);
        }
    }

    /*
    private Rol asignarRol(String opcion) {
        if (opcion.isEmpty()) {
            return Rol.CLIENTE;
        } else {
            return Rol.ENTE;
        }
    }
    * */
    
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Transactional
    public void baja(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            // Obtener la instancia existente
            Usuario usuario = respuesta.get();
            usuario.setActivo(false);
            usuarioRepo.save(usuario);
        }
    }

    @Transactional
    public void alta(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            // Obtener la instancia existente
            Usuario usuario = respuesta.get();
            usuario.setActivo(true);
            usuarioRepo.save(usuario);
        }
    }

    public void validarCliente(String denominacion, Long dni, String direccion, String email, Integer codigoPostal, Integer telefono, String pass, String pass2) throws MyException {
        if (denominacion.isEmpty() || denominacion == null) {
            throw new MyException("El nombre no puede estar vacio");
        }

        if (dni == null) {
            throw new MyException("El dni/cuit no puede estar vacio");
        } else if (dni <= 1000000 || dni >= 100000000000L) {
            throw new MyException("El dni no es valido");
        }

        if (codigoPostal == null) {
            throw new MyException("El codigo postal no puede ser nulo.");
        }

        if (telefono == null) {
            throw new MyException("El telefono no puede ser nulo.");
        }

        if (direccion == null) {
            throw new MyException("La dirección no puede ser nula.");
        }

        if (email.isEmpty()) {
            throw new MyException("El email no puede estar vacio");
        }

        if (pass.isEmpty() || pass.length() <= 5) {
            throw new MyException("La contraseña no puede estar vacia y debe ser de mas de 5 digitos");
        }

        if (!pass.equals(pass2)) {
            throw new MyException("Las contraseñas deben ser iguales");
        }
    }

    public Usuario getOne(String id) {
        return usuarioRepo.getOne(id);
    }
}
