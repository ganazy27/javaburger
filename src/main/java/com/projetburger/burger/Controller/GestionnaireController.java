package com.projetburger.burger.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class GestionnaireController {
    @GetMapping("/menu-gest")
    public String getViewAddStory(Model model) {
        model.addAttribute("message", "A E221");
        return "gestionnaire/menu-gest";
    }
}
