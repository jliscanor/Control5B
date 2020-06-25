package cl.icap.cursofullstack.modulo5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppController {
    @RequestMapping("/")
    public String getHome() {
        return "index";
    }
    
    @RequestMapping("/goles")
    public String getNotas() {
        return "goles";
    }
}
