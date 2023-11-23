package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.enums.Tipo;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.servicios.inmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/crear")
    public String crear() {

        return "inmuebleForm.html";
    }

    @PostMapping("/creado")
    public String creado(@RequestParam Tipo tipo, @RequestParam String ubicacion, @RequestParam Double superficie, @RequestParam Integer ambientes,
            @RequestParam String descripcion, @RequestParam Double precioVenta, @RequestParam Double precioAlquiler, @RequestParam String tipoOferta, ModelMap modelo) {

        try {

            inmuebleServicio.crearInmueble(tipo, ubicacion, superficie, ambientes, descripcion, precioVenta, precioAlquiler, tipoOferta);
            modelo.put("exito", "inmueble creado con exito");
            
            
        } catch (MyException ex) {
            
            modelo.put("error", ex.getMessage());
            return "inmuebleForm.html";
        }

        return "index.html";
        
    }

}
