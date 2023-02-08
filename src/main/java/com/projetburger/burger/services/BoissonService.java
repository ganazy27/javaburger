package com.projetburger.burger.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;
import com.projetburger.burger.models.Boisson;
import com.projetburger.burger.repositories.BoissonRepository;

@Service
@Log
public class BoissonService {
    
    @Autowired
    private BoissonRepository boissonRepository;

    public Boisson addBoisson(Boisson boisson) {
        try {
            boissonRepository.save(boisson);
            return boisson;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Boisson> getAllBoissons() {
        return boissonRepository.findAll();
    }

    
}
