package com.projetburger.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetburger.burger.models.Menu;
import com.projetburger.burger.models.Menu_Burger;
import com.projetburger.burger.models.Menu_Fritte;
import com.projetburger.burger.models.Menu_Taille;
import com.projetburger.burger.repositories.MenuBurgerRepository;
import com.projetburger.burger.repositories.MenuFritteRepository;
import com.projetburger.burger.repositories.MenuRepository;
import com.projetburger.burger.repositories.MenuTailleRepository;

import lombok.extern.java.Log;

@Service
@Log
public class MenuService {
    

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuTailleRepository menuTailleRepository;
    @Autowired
    private MenuBurgerRepository menuBurgerRepository;
    @Autowired
    private MenuFritteRepository menuFritteRepository;



    // ################ service burger ###########

    public Menu addMenu(Menu menu) {
        try {
            menuRepository.save(menu);
            return menu;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }


    // ################## service burger menu ############

    
    // ####ajouter menu burger ####

    public Menu_Burger ajouterMenuBurger(Menu_Burger menuBurger){
        try {
            menuBurgerRepository.save(menuBurger);
            return menuBurger;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu_Burger> ajouterListMenuBurger(List<Menu_Burger> menuBurger){
        try {
            menuBurgerRepository.saveAll(menuBurger);
            return menuBurger;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    // ########### Ajouter MenuFrite #############
    public Menu_Fritte ajouterMenuFrite(Menu_Fritte menuFritte){
        try {
            menuFritteRepository.save(menuFritte);
            return menuFritte;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu_Fritte> ajouterListMenuFrite(List<Menu_Fritte> menuFritte){
        try {
            menuFritteRepository.saveAll(menuFritte);
            return menuFritte;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    // ########### Ajouter MenuTaille #############
    public Menu_Taille ajouterMenuTaille(Menu_Taille menuTaille){
        try {
            menuTailleRepository.save(menuTaille);
            return menuTaille;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu_Taille> ajouterListMenuTaille(List<Menu_Taille> menuTaille){
        try {
            menuTailleRepository.saveAll(menuTaille);
            return menuTaille;
        }catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }



}
