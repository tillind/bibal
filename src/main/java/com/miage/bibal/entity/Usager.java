package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alex
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor //obligatoire pour JPA
@Data
public class Usager implements Serializable{
    @Id
    private String ID;
    private String nom;
    private String prenom;
    private String mail;
    @OneToMany(mappedBy = "usager")
    private Set<Emprunt> emprunts;
    @OneToMany(mappedBy = "usager")
    private List<Reservation> reservations;
    
    public Usager(String nom, String prenom,String mail){
        this.ID = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
}
