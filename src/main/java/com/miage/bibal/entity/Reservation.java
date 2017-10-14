package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
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
public class Reservation implements Serializable{
    @Id
    private String ID;
    private String dateDebut;
    private String dateFin;
    @ManyToOne
    private Usager usager;
    @ManyToOne
    private Oeuvre oeuvre;
    
    
    public Reservation(String dateDebut, String dateFin, Usager usager, Oeuvre oeuvre){
        this.ID = UUID.randomUUID().toString();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.usager = usager;
        this.oeuvre = oeuvre;
    }
    
}
