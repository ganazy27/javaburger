package com.projetburger.burger.Controller.dto;

import com.projetburger.burger.models.Burger;
import com.projetburger.burger.models.Menu;
import com.projetburger.burger.models.Fritte;
import com.projetburger.burger.models.Taille;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {
    private Menu menu;
    private int quantiteFritte;
    private int quantiteBurger;
    private int quantiteTaille;
    private List<Fritte> frittes = new ArrayList<>();
    private List<Burger> burgers = new ArrayList<>();
    private List<Taille> tailles = new ArrayList<>();
   
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public int getQuantiteFritte() {
        return quantiteFritte;
    }
    public void setQuantiteFritte(int quantiteFritte) {
        this.quantiteFritte = quantiteFritte;
    }
    public int getQuantiteBurger() {
        return quantiteBurger;
    }
    public void setQuantiteBurger(int quantiteBurger) {
        this.quantiteBurger = quantiteBurger;
    }
    public int getQuantiteTaille() {
        return quantiteTaille;
    }
    public void setQuantiteTaille(int quantiteTaille) {
        this.quantiteTaille = quantiteTaille;
    }
    public List<Fritte> getFrittes() {
        return frittes;
    }
    public void setFrittes(List<Fritte> frittes) {
        this.frittes = frittes;
    }
    public List<Burger> getBurgers() {
        return burgers;
    }
    public void setBurgers(List<Burger> burgers) {
        this.burgers = burgers;
    }
    public List<Taille> getTailles() {
        return tailles;
    }
    public void setTailles(List<Taille> tailles) {
        this.tailles = tailles;
    }
    
 
    
    

}
