package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.servicios.OfertaServicio;
import Uegg.appInmobiliaria.servicios.inmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gimenez Victor
 */
@Controller
@RequestMapping("/oferta")
public class OfertaControlador {
  @Autowired
    private OfertaServicio ofertaServicio;

    @Autowired
    private inmuebleServicio inmuebleSer;
    @PostMapping("/hacer")
    public String hacerOferta(){
        return "oferta_form";
    }
    @GetMapping("/enviar")
    public String enviar(){
        return "";
    }
}
