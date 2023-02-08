package com.projetburger.burger.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetburger.burger.models.Menu_Burger;
public interface MenuBurgerRepository extends JpaRepository< Menu_Burger ,Long>{
    
}
