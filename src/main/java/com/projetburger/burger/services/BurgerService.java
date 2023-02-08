package com.projetburger.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetburger.burger.models.Burger;
import com.projetburger.burger.repositories.BurgerRepository;

import lombok.extern.java.Log;

@Service
@Log
public class BurgerService {
    

    @Autowired
    private BurgerRepository burgerRepository;



    // ################ service burger ###########

    public Burger addBurger(Burger burger) {
        try {
            burgerRepository.save(burger);
            return burger;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Burger> getAllBurgers() {
        return burgerRepository.findAll();
    }




    public Burger findBurgerById(Long id) {
        return burgerRepository.findById(id).orElse(null);
    }

    public boolean detailBurger(Burger burger) {
        try {
            burgerRepository.delete(burger);
            return true;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }
}
