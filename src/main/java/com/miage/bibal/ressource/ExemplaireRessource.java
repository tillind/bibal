package com.miage.bibal.ressource;

import com.miage.bibal.entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface ExemplaireRessource  extends JpaRepository<Exemplaire, String> {
    
}

