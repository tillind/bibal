package com.miage.bibal.ressource;

import com.miage.bibal.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface EmpruntRessource  extends JpaRepository<Emprunt, String> {
    
}
