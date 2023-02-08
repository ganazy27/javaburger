package com.projetburger.burger.Controller;

import com.projetburger.burger.services.UserService;


import com.projetburger.burger.models.Client;
import com.projetburger.burger.models.Livreur;
import com.projetburger.burger.models.Role;
import com.projetburger.burger.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encode;

    // Mapping login
    @GetMapping("/login")
    public String getLoginView() {
        return "security/login";
    }

    // Mapping admin
    @GetMapping("/gestionnaire/menu-gest")
    public String home(Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("msg", "Bonjour " + user.getNom() + " " + user.getPrenom());
        return "gestionnaire/menu-gest";
    }

    // Mapping inscription client get
    @GetMapping("/inscription")
    public String inscription(Model model){
       // ModelAndView modelAndView = new ModelAndView();
        Client user = new Client();
        model.addAttribute("user", user);
        return "security/inscription";
    }

    // Mapping inscription post client

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public ModelAndView createNewUser( Client user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        Client userExists = (Client) userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("nom", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("security/inscription");
        } else {
            Role role = userService.findRoleByLibelle("CLIENT");
            if (role == null) {
                role = new Role();
                role.setLibelle("CLIENT");
                role = userService.addRole(role);
            }

            user.setRole(role);
            user.setPassword(encode.encode(user.getPassword()));
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Client());
            modelAndView.setViewName("security/inscription");

        }
        return modelAndView;
    }


    // Mapping inscription client get
    @GetMapping("/ajoutLivreur")
    public String ajoutLivreur(Model model){
        // ModelAndView modelAndView = new ModelAndView();
        Livreur user = new Livreur();
        model.addAttribute("user", user);
        return "security/ajoutLivreur";
    }

    // Mapping inscription post client

    @RequestMapping(value = "/ajoutLivreur", method = RequestMethod.POST)
    public ModelAndView createNewLivreur( Livreur user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Livreur userExists = (Livreur) userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("nom", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("security/ajoutLivreur");
        } else {
            Role role = userService.findRoleByLibelle("LIVREUR");
            if (role == null) {
                role = new Role();
                role.setLibelle("LIVREUR");
                role = userService.addRole(role);
            }
            user.setRole(role);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Livreur());
            modelAndView.setViewName("security/ajoutLivreur");

        }
        return modelAndView;
    }

}
