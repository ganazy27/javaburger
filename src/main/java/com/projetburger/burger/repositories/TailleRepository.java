package com.projetburger.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetburger.burger.models.Taille;

public interface TailleRepository extends JpaRepository< Taille ,Long>{
    
}
