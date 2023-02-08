package com.projetburger.burger.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;


@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy ="menu")
    List<Menu_Taille> menuTailles = new ArrayList<>();

    @OneToMany(mappedBy ="menu")
    List<Menu_Burger> menuBurgers = new ArrayList<>();

    @OneToMany(mappedBy ="menu")
    List<Menu_Fritte> menuFrittes = new ArrayList<>();

    private String nom;
    private String image;
    private String description;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Menu_Taille> getMenuTailles() {
        return menuTailles;
    }
    public void setMenuTailles(List<Menu_Taille> menuTailles) {
        this.menuTailles = menuTailles;
    }
    public List<Menu_Burger> getMenuBurgers() {
        return menuBurgers;
    }
    public void setMenuBurgers(List<Menu_Burger> menuBurgers) {
        this.menuBurgers = menuBurgers;
    }
    public List<Menu_Fritte> getMenuFrittes() {
        return menuFrittes;
    }
    public void setMenuFrittes(List<Menu_Fritte> menuFrittes) {
        this.menuFrittes = menuFrittes;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    

    
    
}
