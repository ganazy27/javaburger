package com.projetburger.burger.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("GESTIONNAIRE")
@Getter
@Setter
public class Gestionnaire extends User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
