package com.projetburger.burger.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetburger.burger.models.Marque;

public interface MarqueRepository extends JpaRepository< Marque ,Long>{
    
}
