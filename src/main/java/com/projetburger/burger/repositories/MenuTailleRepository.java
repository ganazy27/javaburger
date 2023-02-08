package com.projetburger.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetburger.burger.models.Menu_Taille;

public interface MenuTailleRepository extends JpaRepository<Menu_Taille ,Long>{
    
}
