package com.miage.bibal.ressource;

import com.miage.bibal.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface LivreRessource  extends JpaRepository<Livre, String> {
    
}

