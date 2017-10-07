/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bibal.ressource;

import com.miage.bibal.entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alex
 */
public interface UsagerRessource extends JpaRepository<Usager, String> {
    
}
