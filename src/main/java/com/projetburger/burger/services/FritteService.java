
package com.projetburger.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetburger.burger.models.Fritte;
import com.projetburger.burger.repositories.FritteRepository;

import lombok.extern.java.Log;

@Service
@Log
public class FritteService {
    

    @Autowired
    private FritteRepository fritteRepository;



    // ################ Fritte Service ###########

    public Fritte addFritte(Fritte fritte) {
        try {
            fritteRepository.save(fritte);
            return fritte;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Fritte> getAllFrittes() {
        return fritteRepository.findAll();
    }
}
