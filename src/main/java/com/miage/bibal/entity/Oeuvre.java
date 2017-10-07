package com.miage.bibal.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Oeuvre implements Serializable{

    @OneToMany(mappedBy = "oeuvre")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "oeuvre")
    private Set<Exemplaire> exemplaires;
    @Id
    private String ID;
    private String nom;
    private String synopsis;
    
    
}
