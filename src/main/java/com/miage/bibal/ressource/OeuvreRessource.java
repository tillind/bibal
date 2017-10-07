package com.miage.bibal.ressource;

import com.miage.bibal.entity.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface OeuvreRessource  extends JpaRepository<Oeuvre, String> {
    
}
