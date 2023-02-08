package com.projetburger.burger.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetburger.burger.Controller.dto.MenuDTO;
import com.projetburger.burger.models.Burger;
import com.projetburger.burger.models.Fritte;
import com.projetburger.burger.models.Menu;
import com.projetburger.burger.models.Menu_Burger;
import com.projetburger.burger.models.Menu_Fritte;
import com.projetburger.burger.models.Menu_Taille;
import com.projetburger.burger.models.Taille;
import com.projetburger.burger.services.MenuService;
import com.projetburger.burger.services.TailleService;
import com.projetburger.burger.services.BurgerService;
import com.projetburger.burger.services.FritteService;



@Controller
public class menuController {
    
    @Autowired
    private MenuService menuService;
    @Autowired
    private BurgerService burgerService;
    @Autowired
    private FritteService fritteService;
    @Autowired
    private TailleService tailleService;

    @GetMapping("/menu-add")
    public String getViewAddMenu(Model model) {
        Menu menu = new Menu();
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenu(menu);
         List<Burger> burgers = burgerService.getAllBurgers();
        model.addAttribute("burgers", burgers);
        List<Fritte> frittes = fritteService.getAllFrittes();
        model.addAttribute("frittes", frittes);
        List<Taille> tailles = tailleService.getAllTailles();
        model.addAttribute("tailles", tailles); 

        model.addAttribute("menu", menuDTO);
        return "menu/menu-add";
    }


    @GetMapping("/menu")
    public String getViewListProduit(Model model) {
        
        List<Menu> menus = menuService.getAllMenus();

        model.addAttribute("menus", menus);
        return "menu/menu";
    }

    
    public static String uploadDirectory = System.getProperty("user.dir") +"/src/main/resources/static/image";

    @PostMapping("/menu-add")
    public String addMenu(@ModelAttribute("menu") MenuDTO menuDTO, Model model,@RequestParam("fileImage") MultipartFile fileImage) throws IOException {
       Menu menu = menuService.addMenu(menuDTO.getMenu());

        if (menu == null || menu.getNom() == null || menu.getNom().equals("")) {
            model.addAttribute("errorNom", "Champ obligatoire");
            return "menu/menu-add"; 
        }
        boolean valide = true;
        String image;
        if(valide) {
            if (!fileImage.isEmpty()) {
                image = fileImage.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDirectory, image);
                Files.write(fileNameAndPath, fileImage.getBytes());
                menu.setImage(image);
                menuService.addMenu(menu);
            }
            menuService.addMenu(menu);
            
            List<Menu_Burger> menuBurgers = new ArrayList<>();
            List<Menu_Fritte> menuFrittes = new ArrayList<>();
            List<Menu_Taille> menuTailles = new ArrayList<>();

            for(Burger burger : menuDTO.getBurgers()) {
                Menu_Burger menuBurger = new Menu_Burger();
                menuBurger.setBurger(burger);
                menuBurger.setMenu(menu);
                menuBurger.setQuantity(menuDTO.getQuantiteBurger());

                menuBurgers.add(menuBurger);
            }

            for(Fritte fritte : menuDTO.getFrittes()){
                Menu_Fritte menuFritte = new Menu_Fritte();

                menuFritte.setFritte(fritte);
                menuFritte.setMenu(menu);
                menuFritte.setQuantity(menuDTO.getQuantiteFritte());

                menuFrittes.add(menuFritte);
            }
            for(Taille taille : menuDTO.getTailles()){
                Menu_Taille menuTaille = new Menu_Taille();

                menuTaille.setTaille(taille);
                menuTaille.setMenu(menu);
                menuTaille.setQuantity(menuDTO.getQuantiteTaille());
                menuTailles.add(menuTaille);
            }
            menuService.ajouterListMenuFrite(menuFrittes);
            menuService.ajouterListMenuBurger(menuBurgers);
            menuService.ajouterListMenuTaille(menuTailles);
             if (menu.getId() != null) {
                return "redirect:/menu-add";
            } else {
                model.addAttribute("error", "Echec de l'operation");
                return "menu/menu-add";
            }
        }
        return "menu/menu-add";
    }
 
}
