package com.miage.bibal.ressource;

import com.miage.bibal.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface ReservationRessource  extends JpaRepository<Reservation, String> {
    
}

