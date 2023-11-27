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

/**
 *
 * @author Gimenez Victor
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Transactional
    public void crear(String nombre, String apellido, Long dni, Long idTributario, String direccion, String ubicacion, Integer telefono, 
            String email, String pass, String pass2, String opcion) throws MyException {
        validar(nombre, apellido, dni, idTributario, email, pass, pass2);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setIdTributario(idTributario);
        usuario.setUbicacion(ubicacion);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPass(pass);
        usuario.setRol(asignarRol(opcion));
        usuario.setFechaAlta(new Date());
        usuario.setActivo(true);
        usuarioRepo.save(usuario);
    }

    @Transactional
    public void modificarUsuario(String id, Imagen imagen, Integer telefono, Long idTributario, String direccion, String ubicacion) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setImagen(imagen);
            usuario.setTelefono(telefono);
            usuario.setIdTributario(idTributario);
            usuario.setDireccion(direccion);
            usuario.setUbicacion(ubicacion);

            usuarioRepo.save(usuario);
        }
    }

    @Transactional
    public void modificarRol(String id, String rol) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setRol(Rol.valueOf(rol));
            usuarioRepo.save(usuario);
        }
    }

    private Rol asignarRol(String opcion) {
        if (opcion.isEmpty()) {
            return Rol.CLIENTE;
        } else {
            return Rol.ENTE;
        }
    }

    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Transactional
    public void baja(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setActivo(false);
            usuarioRepo.save(usuario);
        }
    }

    @Transactional
    public void alta(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setActivo(true);
            usuarioRepo.save(usuario);
        }
    }

    public void validar(String nombre, String apellido, Long dni, Long idTributario, String email, String pass, String pass2) throws MyException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre no puede estar vacio");
        }

        if (apellido.isEmpty() || apellido == null) {
            throw new MyException("El campo apellido no puede estar vacio");
        }

        if (dni == null) {

            throw new MyException("El dni no puede estar vacio");
        } else if (dni <= 0 || dni > 99999999) {
            throw new MyException("El dni no es valido");
        }

        if (idTributario == null) {
            throw new MyException("El cuil / cuit no puede ser nulo.");
        }

        if (email.isEmpty() ) {
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
