package com.miage.bibal.ressource;

import com.miage.bibal.entity.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface MagazineRessource  extends JpaRepository<Magazine, String> {
    
}
