package com.projetburger.burger.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetburger.burger.models.Boisson;
public interface BoissonRepository extends JpaRepository< Boisson ,Long>{
    
}
