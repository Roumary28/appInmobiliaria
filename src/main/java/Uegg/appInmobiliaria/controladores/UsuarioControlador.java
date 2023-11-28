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
     public String crear(){
         
        return "usuario_form.html";
    }
     
    @PostMapping("/creado")
    public String creado(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam Long idTributario,
            @RequestParam Long dni,
            @RequestParam Integer telefono,
            @RequestParam String direccion,
            @RequestParam String ubicacion,
            @RequestParam String email,
            @RequestParam String pass,
            @RequestParam String pass2,
            @RequestParam(required = false) String opcion,
            ModelMap modelo
    ) throws MyException{
        try {
            usuarioServicio.crear(nombre, apellido, dni, idTributario, email, pass, pass2, opcion);
            modelo.put("exito", "usuario registrado con exito");
            return "login.html";
        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("idTributario", idTributario);
            modelo.put("dni", dni);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);
            modelo.put("ubicacion", ubicacion);
            modelo.put("email", email);
            modelo.put("pass", pass);
            modelo.put("pass2", pass2);
            modelo.put("opcion", opcion);
            return "redirect:../usuario_form.html";
            
        }
        
    }
    
   @GetMapping("/lista") //localhost:8080/libro/lista
    public String lista(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();
        modelo.put("usuarios", usuarios);
        return "usuario_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(){
        return "usuario_modificar.html";
    }
    
    
    
   
}
