package es.uniovi.asw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("/")
    public String landing(Model model) {
        return "index";
    }
    
    @RequestMapping("/welcome")
    public String welcome(Model model){ 
        return "index";
    }
    
    @RequestMapping("/hello")
    public String hola(Model model) {
        return "hello";
    }
    
    @RequestMapping("/info")
    public String info(Model model) {
    	//model.addAttribute("nombre", "Luis");
    	//do something when showing info
        return "info";
    }
    
    @RequestMapping("/login")
    public String login(Model model) {
    	//model.addAttribute("nombre", "Luis");
    	//do something with login
        return "login";
    }

}