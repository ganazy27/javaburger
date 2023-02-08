package com.projetburger.burger.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;
import com.projetburger.burger.models.Marque;
import com.projetburger.burger.repositories.MarqueRepository;


@Service
@Log
public class MarqueService {
    
    @Autowired
    private MarqueRepository marqueRepository;

    public Marque addMarque(Marque marque) {
        try {
            marqueRepository.save(marque);
            return marque;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }
}
