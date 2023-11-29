package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gimenez Victor
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/crear")
    public String crear() {

        return "usuario_form.html";
    }

    @PostMapping("/creadoCliente")
    public String creadoCliente(
            @RequestParam String denominacion,
            @RequestParam Long dni,
            @RequestParam Integer telefono,
            @RequestParam Integer codigoPostal,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String pass,
            @RequestParam String pass2,
            ModelMap modelo
    ) throws MyException {
        try {

            usuarioServicio.crearCliente(denominacion, dni, direccion, codigoPostal, telefono, email, pass, pass2);
            modelo.put("exito", "usuario registrado con exito");
            return "login.html";

        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            modelo.put("denominacion", denominacion);
            modelo.put("dni", dni);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);
            modelo.put("codigoPostal", codigoPostal);
            modelo.put("email", email);
            modelo.put("pass", pass);
            modelo.put("pass2", pass2);
            return "redirect:../usuario_form.html";
        }
    }

    @PostMapping("/creadoEnte")
    public String creadoEnte(
            @RequestParam String denominacion,
            @RequestParam Long cuit,
            @RequestParam Integer telefono,
            @RequestParam Integer codigoPostal,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String pass,
            @RequestParam String pass2,
            ModelMap modelo
    ) throws MyException {
        try {

            usuarioServicio.crearEnte(denominacion, cuit, direccion, codigoPostal, telefono, email, pass, pass2);
            modelo.put("exito", "usuario registrado con exito");
            return "login.html";

        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            modelo.put("denominacion", denominacion);
            modelo.put("cuit", cuit);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);
            modelo.put("codigoPostal", codigoPostal);
            modelo.put("email", email);
            modelo.put("pass", pass);
            modelo.put("pass2", pass2);
            return "redirect:../usuario_form.html";
        }
    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();
        modelo.put("usuarios", usuarios);
        return "usuario_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar() {
        return "usuario_modificar.html";
    }

}
