package com.miage.bibal.ressource;

import com.miage.bibal.entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface AuteurRessource  extends JpaRepository<Auteur, String> {
    
}
