package es.uniovi.asw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("/")
    public String landing(Model model) {
    	//model.addAttribute("nombre", "Amigo");
        return "index";
    }
    
    @RequestMapping("/hola")
    public String hola(Model model) {
    	//model.addAttribute("nombre", "Luis");
        return "index";
    }
    
    @RequestMapping("/info")
    public String info(Model model) {
    	//model.addAttribute("nombre", "Luis");
        return "info";
    }

}