package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.repositorios.InmuebleRepositorio;
import Uegg.appInmobiliaria.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    InmuebleRepositorio inmuebleRepositorio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();
        modelo.addAttribute("usuarios", usuarios);

        return "usuarioList.html";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {

        usuarioServicio.modificarRol(id);

        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/darAlta/{id}")
    public String darAlta(@PathVariable String id) {
        usuarioServicio.alta(id);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/darBaja/{id}")
    public String darBaja(@PathVariable String id) {
        usuarioServicio.baja(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {

        List<Inmueble> respuesta = inmuebleRepositorio.buscarPorPropietario(id);
        if (respuesta.isEmpty()) {
            usuarioServicio.baja(id);
            return "redirect:/admin/usuarios";
        }else {
            return "redirect:/inmueble/eliminar";
        }

        
    }

}
