package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @ElementCollection
    @CollectionTable(name="emprunt", joinColumns=@JoinColumn(name="ID"))
    private Set<String> emprunts;
    @ElementCollection
    @CollectionTable(name="reservation", joinColumns=@JoinColumn(name="ID"))
    private List<String> reservations;
    
    public Usager(String nom, String prenom,String mail){
        this.ID = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
}
