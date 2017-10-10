
package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
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
public class Exemplaire implements Serializable{
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAchat;
    @Enumerated(EnumType.ORDINAL)
    private E_Etat_Exemplaire etat;
    @ElementCollection
    @CollectionTable(name="emprunt", joinColumns=@JoinColumn(name="ID"))
    private Set<String> emprunts;
    @ManyToOne
    private Oeuvre oeuvre;
    
    public  Exemplaire(Date dateAchat){
        this.id = UUID.randomUUID().toString();
        this.dateAchat = dateAchat;
        this.etat = E_Etat_Exemplaire.NEUF;
    }
    
}
