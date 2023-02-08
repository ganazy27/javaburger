package com.projetburger.burger.models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LIVREUR")
public class Livreur extends User{

    private String immatriculation;
    private String telephone;


    /**
     * get field
     *
     * @return immatriculation
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * set field
     *
     * @param immatriculation
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * get field
     *
     * @return telephone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * set field
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
