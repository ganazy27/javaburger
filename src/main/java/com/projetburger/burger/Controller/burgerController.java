package com.projetburger.burger.Controller;

import java.io.IOException;
import java.nio.file.Files; 
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetburger.burger.models.Burger;
import com.projetburger.burger.repositories.BurgerRepository;
import com.projetburger.burger.services.BurgerService;

@Controller
public class burgerController {
    @Autowired
    private BurgerService burgerService;
    @Autowired
    private BurgerRepository burgerRepository;
    @GetMapping("/burger-add")
    public String getViewAddBurger(Model model) {
        Burger burger = new Burger();

        model.addAttribute("burger", burger);
        return "burger/burger-add";
    }

    @GetMapping("/burger")
    public String getViewListProduit(Model model,@RequestParam(name = "page",defaultValue = "0")int p) {
        Page <Burger> burgers =burgerRepository.findAll(PageRequest.of(p, 2));
        //List<Burger> burgers = burgerService.getAllBurgers();
        int NumPage = burgers.getTotalPages();
        int pages[] = new int[NumPage];
        for(int i = 0;i<NumPage;i++)pages[i]=i;
        model.addAttribute("pages", pages);

        model.addAttribute("burgers", burgers);
        return "burger/burger";
    }

  


    @GetMapping("/burger-detail/{id}")
    public String getViewDetailBurger(@PathVariable Long id, Model model) {
        Burger burgers = burgerService.findBurgerById(id);
      

      
        model.addAttribute("burgers", burgers);

        return "burger/burger-detail";
    }





public static String uploadDirectory = System.getProperty("user.dir") +"/src/main/resources/static/image";
    @PostMapping("/burger-add")
    public String addBurger(@ModelAttribute("burger") Burger burger,Model model,@RequestParam("fileImage")MultipartFile fileImage) throws IOException{
        
        if(burger == null) {
            model.addAttribute("error", "burger incorrect ");
            return "burger/burger-add"; 
        }

        boolean valide = true;

        if (burger.getNom() == null || burger.getNom().equals("")) {
            model.addAttribute("errorNom", "Nom obligatoire ");
            valide = false;
        }

        if (burger.getPrix() == 0) {
            model.addAttribute("errorPrix", "prix obligatoire ");
            valide = false;
        }
        if (burger.getDescription() == null || burger.getNom().equals("")) {
            model.addAttribute("errorNom", "Description obligatoire ");
            valide = false;
        }
        String image ;
        if (valide) {
            if(!fileImage.isEmpty()){
                image = fileImage.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDirectory, image);
                Files.write(fileNameAndPath, fileImage.getBytes());
                burger.setImage(image);
                burgerService.addBurger(burger);
                }  
            burgerService.addBurger(burger);
            return "redirect:/burger";
        }
       
        return "burger/burger-add";
    }

    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String formEdit(Model model, Long id) {
        Burger burger = burgerRepository.getOne(id);
        model.addAttribute("burger", burger);
        return "burger/editerProduit";
    }
    @PostMapping("/update/{id}")
    public String updateBurger(@PathVariable("id") long id, Model model,Burger burger,BindingResult result,
                               @RequestParam("fileImage")MultipartFile fileImage ) throws IOException {

        if (result.hasErrors()) {
            burger.setId(id);
            return "burger/editerProduit";
        }
        String imageUUID;
        if(!fileImage.isEmpty()){
            imageUUID = fileImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, fileImage.getBytes());
            burger.setImage(imageUUID);

        }
        burgerService.addBurger(burger);
        return "redirect:/burger";
    }
}
