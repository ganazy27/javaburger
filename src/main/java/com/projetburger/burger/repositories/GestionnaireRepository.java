package com.projetburger.burger.repositories;


import com.projetburger.burger.models.Gestionnaire;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {
    Gestionnaire findByEmail(String email);
    
}
