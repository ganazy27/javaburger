package com.projetburger.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetburger.burger.models.Menu;

public interface MenuRepository extends JpaRepository< Menu ,Long>{
    
}
