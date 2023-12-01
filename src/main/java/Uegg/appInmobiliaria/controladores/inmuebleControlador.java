package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.enums.Tipo;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.servicios.inmuebleServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inmueble")
public class inmuebleControlador {

    @Autowired
    private inmuebleServicio inmuebleServicio;
    
    @PreAuthorize("hasAnyRole('ROLE_ENTE', 'ROLE_ADMIN')")
    @GetMapping("/crear")
    public String crear(ModelMap modelo) {
        
        modelo.addAttribute("tipos", Tipo.values());

        return "inmuebleForm.html";
    }

    @PostMapping("/creado")
    public String creado(@RequestParam Tipo tipo, @RequestParam String ubicacion, @RequestParam (required = false) Double superficie, @RequestParam (required = false) Integer ambientes,
            @RequestParam String descripcion, @RequestParam (required = false) Double precio, @RequestParam (required = false) String tipoOferta, ModelMap modelo) {

        try {
            if (tipoOferta.equals("venta")) {
                Double precioVenta = precio;
                Double precioAlquiler = null;
                inmuebleServicio.crearInmueble(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);
            } else if (tipoOferta.equals( "alquiler")) {
                Double precioAlquiler  = precio;
                Double precioVenta = null;
                inmuebleServicio.crearInmueble(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);
            }
            
            modelo.put("exito", "inmueble creado con exito");
            
            
        } catch (MyException ex) {
            
            modelo.put("error", ex.getMessage());
            modelo.addAttribute("tipos", Tipo.values());
            return "inmuebleForm.html";
        }

        return "index.html";
        
    }

    @GetMapping("/lista/casaAlquiler") //localhost:8080/inmueble/lista/
    public String listarCasaAlquiler(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.CASA, "alquiler");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/departamentoAlquiler") //localhost:8080/inmueble/lista/
    public String listarDepartamentoAlquiler(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.DEPARTAMENTO, "alquiler");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/loteAlquiler") //localhost:8080/inmueble/lista/
    public String listarLoteAlquiler(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.LOTE, "alquiler");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/localAlquiler") //localhost:8080/inmueble/lista/
    public String listarLocalAlquiler(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.LOCAL, "alquiler");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/casaVenta") //localhost:8080/inmueble/lista/
    public String listarCasaVenta(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.CASA, "venta");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/departamentoVenta") //localhost:8080/inmueble/lista/
    public String listarDepartamentoVenta(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.DEPARTAMENTO, "venta");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/localVenta") //localhost:8080/inmueble/lista/
    public String listarLocalVenta(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.LOCAL, "venta");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
    @GetMapping("/lista/loteVenta") //localhost:8080/inmueble/lista/
    public String listarLoteVenta(ModelMap modelo) {
            
        List<Inmueble> inmuebles = inmuebleServicio.listarTipoInmueble(Tipo.LOTE, "venta");
        
        modelo.addAttribute("inmuebles", inmuebles);
        
        return "inmuebleList.html";
    }
    
}
