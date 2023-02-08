package com.projetburger.burger.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.projetburger.burger.models.Boisson;
import com.projetburger.burger.services.BoissonService;
import com.projetburger.burger.models.Marque;
import com.projetburger.burger.services.MarqueService;
import com.projetburger.burger.models.Taille;
import com.projetburger.burger.repositories.BoissonRepository;
import com.projetburger.burger.services.TailleService;

@Controller
public class boissonController {
    @Autowired
    private BoissonService boissonService;
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private TailleService tailleService;
    @Autowired
    private BoissonRepository boissonRepository;

    @GetMapping("/boisson")
    public String getViewListProduit(Model model ,@RequestParam(name = "page",defaultValue = "0")int p) {
        Page <Boisson> boissons =boissonRepository.findAll(PageRequest.of(p, 2));

        //List<Boisson> boissons = boissonService.getAllBoissons();
        int NumPage = boissons.getTotalPages();
        int pages[] = new int[NumPage];
        for(int i = 0;i<NumPage;i++)pages[i]=i;
        model.addAttribute("pages", pages);
        model.addAttribute("boissons", boissons);
        return "boisson/boisson";
    }

    @GetMapping("/boisson-add")
    public String getViewAddBoisson (Model model) {
        Boisson boisson = new Boisson();
        List<Marque> marques = marqueService.getAllMarques();
        List<Taille> tailles = tailleService.getAllTailles();

        model.addAttribute("boisson", boisson);
        model.addAttribute("marques", marques);
        model.addAttribute("tailles", tailles);
        return "boisson/boisson-add";
    }

    public static String uploadDirectory = System.getProperty("user.dir") +"/src/main/resources/static/image";
    @PostMapping("/boisson-add")
    public String addBoisson(@ModelAttribute("boisson") Boisson boisson,Model model,@RequestParam("fileImage")MultipartFile fileImage) throws IOException{

        if(boisson == null) {
            model.addAttribute("error", "boisson incorrect ");
            return "boisson/boisson-add"; 
        }
        boolean valide = true;

        if (boisson.getNom() == null || boisson.getNom().equals("")) {
            model.addAttribute("errorNom", "Nom obligatoire ");
            valide = false;
        }
        if (boisson.getPrix() == 0) {
            model.addAttribute("errorPrix", "prix obligatoire ");
            valide = false;
        }
      /*   if (boisson.getMarque() == null || boisson.getMarque().getId() == null){
            model.addAttribute("errorPrix", "marque obligatoire ");
            valide = false;
        }
        if (boisson.getTaille() == null || boisson.getTaille().getId() == null){
            model.addAttribute("errorPrix", "taille obligatoire ");
            valide = false;
        } */
        
        String image ;
        if (valide) {
            if(!fileImage.isEmpty()){
                image = fileImage.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDirectory, image);
                Files.write(fileNameAndPath, fileImage.getBytes());
                boisson.setImage(image);
                boissonService.addBoisson(boisson);
                }  
                boissonService.addBoisson(boisson);
            return "redirect:/boisson";
        }
       
        return "boisson/boisson-add";
    
    } 
}
