package com.projetburger.burger.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
@Getter
@Setter
public class Client extends User{

private String telephone;

   /* @Embedded
    private Adresse adress;*/



    private String adresse;


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
   /* public Adresse getAdress() {
        return adress;
    }

    public void setAdress(Adresse adress) {
        this.adress = adress;
    }*/

}
