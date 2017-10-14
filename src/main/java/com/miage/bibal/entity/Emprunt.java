package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Emprunt implements Serializable {
    @Id
    private String ID;
    private String dateDebut;
    private String dateFin;
    @ManyToOne
    private Usager usager;

    @ManyToOne
    private Exemplaire exemplaire;
    @Enumerated(EnumType.ORDINAL)
    private E_Etat_Emprunt etat;
    
    public Emprunt(String dateDebut,String dateFin,Usager usager,Exemplaire exemplaire, E_Etat_Emprunt etat){
        this.ID = UUID.randomUUID().toString();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.etat = etat;        
    }
}
