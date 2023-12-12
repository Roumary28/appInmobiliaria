package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Oferta;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.servicios.InmuebleServicio;
import Uegg.appInmobiliaria.servicios.OfertaServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gimenez Victor
 */
@Controller
@RequestMapping("/oferta")
public class OfertaControlador {

    @Autowired
    private OfertaServicio ofertaServicio;
    @Autowired
    private InmuebleServicio inmuebleServicio;

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
    @GetMapping("/crear/{id}")
    public String hacerOferta(
            @PathVariable("id") String id,
            HttpSession session,
            ModelMap modelo
    ) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String idCliente = ((Usuario) auth.getPrincipal()).getId();
//        modelo.addAttribute("idCliente", idCliente);
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("inmueble", inmuebleServicio.getOne(id));
        modelo.put("usuario", usuario);
        return "oferta_form";
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
    @PostMapping("/enviar")
    public String enviarOferta(
            @RequestParam(required = false) Double montoOferta,
            @RequestParam("idInmueble") String idInmueble,
            @RequestParam("idCliente") String idCliente,
            ModelMap modelo
    ) throws MyException {

        try {
            ofertaServicio.crearOfertaCliente(montoOferta, idInmueble, idCliente);

            modelo.put("exito", "Oferta enviada. Espere respuesta del Dueño de la propiedad");
            System.out.println("Enviado");
            return "redirect:/";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("montoOferta", montoOferta);
            modelo.put("idInmueble", idInmueble);
            modelo.put("idCliente", idCliente);
            System.out.println("Error");
            return "oferta_form";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ENTE', 'ROLE_ADMIN')")
    @GetMapping("/inmueble/{idInmueble}")
    public String listarOfertasInmuebles(
            @PathVariable String idInmueble,
            ModelMap modelo
    ) {
        List<Oferta> ofertas = ofertaServicio.listarOfertasInmueble(idInmueble);
        Oferta oferta = ofertaServicio.mejorOferta(idInmueble);
        modelo.addAttribute("oferta", oferta);
        modelo.addAttribute("ofertas", ofertas);
        return "oferta_inmueble_list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_ENTE', 'ROLE_ADMIN')")
    @GetMapping("/cliente/{idCliente}")
    public String listarOfertasClientes(
            @PathVariable String idCliente,
            ModelMap modelo
    ) {
        List<Oferta> ofertas = ofertaServicio.listarOfertaCliente(idCliente);
        modelo.addAttribute("ofertas", ofertas);
        return "oferta_cliente_list.html";
    }
    
   @GetMapping("/eliminar/{id}")
   public String eliminarOferta (@PathVariable String id, HttpSession session) {
       ofertaServicio.eliminarOferta(id);
       
       return "redirect:/";
   }
   
}
