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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.projetburger.burger.models.Fritte;
import com.projetburger.burger.repositories.FritteRepository;
import com.projetburger.burger.services.FritteService;

@Controller
public class fritteController {
    
    @Autowired
    private FritteService fritteService;
    @Autowired
    private FritteRepository fritteRepository;

    @GetMapping("/fritte-add")
    public String getViewAddFritte(Model model) {
        Fritte fritte = new Fritte();

        model.addAttribute("fritte", fritte);
        return "fritte/fritte-add";
    }

    @GetMapping("/fritte")
    public String getViewListProduit(Model model ,@RequestParam(name = "page",defaultValue = "0")int p) {
        Page <Fritte> frittes =fritteRepository.findAll(PageRequest.of(p, 2));

        //List<Fritte> frittes = fritteService.getAllFrittes();
        int NumPage = frittes.getTotalPages();
        int pages[] = new int[NumPage];
        for(int i = 0;i<NumPage;i++)pages[i]=i;
        model.addAttribute("pages", pages);

        model.addAttribute("frittes", frittes);
        return "fritte/fritte";
    }


public static String uploadDirectory = System.getProperty("user.dir") +"/src/main/resources/static/image";
@PostMapping("/fritte-add")
public String addFritte(@ModelAttribute("fritte") Fritte fritte,Model model,@RequestParam("fileImage")MultipartFile fileImage) throws IOException{
    
    if(fritte == null) {
        model.addAttribute("error", "fritte incorrect ");
        return "fritte/fritte-add"; 
    }

    boolean valide = true;

    if (fritte.getNom() == null || fritte.getNom().equals("")) {
        model.addAttribute("errorNom", "Nom obligatoire ");
        valide = false;
    }
    if (fritte.getPrix() == 0) {
        model.addAttribute("errorPrix", "prix obligatoire ");
        valide = false;
    }

    
   
    String image ;
    if (valide) {
        if(!fileImage.isEmpty()){
            image = fileImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, image);
            Files.write(fileNameAndPath, fileImage.getBytes());
            fritte.setImage(image);
            fritteService.addFritte(fritte);
            }  
            fritteService.addFritte(fritte);
        return "redirect:/fritte";
    }
   
    return "fritte/fritte-add";
}

}
