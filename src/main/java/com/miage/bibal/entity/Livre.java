package com.miage.bibal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name = "ID")
public class Livre extends Oeuvre implements Serializable{
    private String auteur;    
}
