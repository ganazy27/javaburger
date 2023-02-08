package com.projetburger.burger.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projetburger.burger.models.Burger;
import com.projetburger.burger.services.BurgerService;
import com.projetburger.burger.models.Menu;
import com.projetburger.burger.services.MenuService;
import com.projetburger.burger.models.Boisson;
import com.projetburger.burger.services.BoissonService;
import com.projetburger.burger.models.Fritte;
import com.projetburger.burger.services.FritteService;

@Controller
public class CatalogueController {
    @Autowired
    private BurgerService burgerService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private FritteService fritteService;
    @Autowired
    private BoissonService boissonService;
    @GetMapping({"/","/index"})
    public String index(Model model) {

        List<Burger> burgers = burgerService.getAllBurgers();
        model.addAttribute("burgers", burgers);

        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
         List<Boisson> boissons = boissonService.getAllBoissons();
         model.addAttribute("boissons", boissons);

         List<Fritte> frittes = fritteService.getAllFrittes();
                 model.addAttribute("frittes", frittes);


        
        return "index";
    }
    
    @GetMapping("/story")
    public String getViewAddStory(Model model) {
        model.addAttribute("message", "A E221");
        return "catalogue/story";
    }

    @GetMapping("/vv")
    public String getViewAddMenu(Model model) {
        model.addAttribute("message", "A E221");
        return "catalogue/vv";
    }
    @GetMapping("/gallerie")
    public String getViewAddGallerie(Model model) {
        model.addAttribute("message", "A E221");
        return "catalogue/gallerie";
    }
    @GetMapping("/contact")
    public String getViewAddContact(Model model) {
        model.addAttribute("message", "A E221");
        return "catalogue/contact";
    }
    
}
