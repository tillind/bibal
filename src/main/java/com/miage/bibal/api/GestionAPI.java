/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bibal.api;

import com.miage.bibal.entity.E_Etat_Emprunt;
import com.miage.bibal.entity.Emprunt;
import com.miage.bibal.entity.Oeuvre;
import com.miage.bibal.entity.Reservation;
import com.miage.bibal.ressource.AuteurRessource;
import com.miage.bibal.ressource.EmpruntRessource;
import com.miage.bibal.ressource.EntityToRessource;
import com.miage.bibal.ressource.ExemplaireRessource;
import com.miage.bibal.ressource.LivreRessource;
import com.miage.bibal.ressource.MagazineRessource;
import com.miage.bibal.ressource.OeuvreRessource;
import com.miage.bibal.ressource.ReservationRessource;
import com.miage.bibal.ressource.UsagerRessource;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alex
 */
@RestController
@CrossOrigin
@RequestMapping(value="/gestion",produces=MediaType.APPLICATION_JSON_VALUE)
public class GestionAPI {
    public OeuvreRessource or;
    public ExemplaireRessource er;
    public UsagerRessource ur;
    public ReservationRessource rr;
    public EmpruntRessource emr;
    
    @Autowired
    public GestionAPI(OeuvreRessource or,ExemplaireRessource er,UsagerRessource ur,ReservationRessource rr,EmpruntRessource emr){
        this.or = or;
        this.er = er;
        this.ur = ur;
        this.rr = rr;
        this.emr = emr;
    }
    /**************************************************************************
    ************************PARTIE RESERVATION*********************************    
    **************************************************************************/
    
    @GetMapping(value = "/reservations")
    public ResponseEntity<?> getAllReservation(){
        Iterable<Reservation> resaCollection = rr.findAll();
        return new ResponseEntity<>(EntityToRessource.reservationToRessource(resaCollection),HttpStatus.OK);
    }
    
    @GetMapping(value = "/reservation/{resaId}")
    public ResponseEntity<?> getReservation(@PathVariable("resaId") String id){
        return Optional.ofNullable(rr.findOne(id))
                .map(r -> new ResponseEntity<>(EntityToRessource.reservationToRessource(r, true),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping(value = "/reservations")
    public ResponseEntity<?> createResa(@RequestBody Reservation resa,@PathVariable("idOeuvre") String idOeuvre , @PathVariable("idUsager") String idUsager){
        resa.setID(UUID.randomUUID().toString());
        resa.setOeuvre(or.findOne(idOeuvre));
        resa.setUsager(ur.findOne(idUsager));
        Reservation tmp = rr.save(resa);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(GestionAPI.class).slash(tmp.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);   
    }
    
    @DeleteMapping(value = "/reservations/{resaId}")
    public ResponseEntity<?> deleteReservation(@PathVariable("resaId") String resaId ){
        Reservation reservation = rr.findOne(resaId);
        rr.delete(resaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    /**************************************************************************
    ***************************PARTIE EMPRUNT**********************************    
    **************************************************************************/
    
    @GetMapping(value = "/emprunts")
    public ResponseEntity<?> getAllEmprunt(){
        Iterable<Emprunt> resaCollection = emr.findAll();
        return new ResponseEntity<>(EntityToRessource.empruntToRessource(resaCollection),HttpStatus.OK);
    }
    
    @GetMapping(value = "/emprunt/{empId}")
    public ResponseEntity<?> getEmprunt(@PathVariable("empId") String id){
        return Optional.ofNullable(emr.findOne(id))
                .map(r -> new ResponseEntity<>(EntityToRessource.empruntToRessource(r, true),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping(value = "/emprunt")
    public ResponseEntity<?> createEmprunt(@RequestBody Emprunt emprunt){
        emprunt.setID(UUID.randomUUID().toString());
        
        emprunt.setEtat(E_Etat_Emprunt.EN_COURS);
        
        Emprunt tmp = emr.save(emprunt);
        ur.findOne(emprunt.getUsager().getID()).getEmprunts().add(emprunt.getID());
        er.findOne(emprunt.getExemplaire().getId()).getEmprunts().add(emprunt.getID());
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(GestionAPI.class).slash(tmp.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);   
    }
    
    @DeleteMapping(value = "/emprunt/{empId}")
    public ResponseEntity<?> deleteEmprunt(@PathVariable("empId") String empId ){
        Emprunt emprunt = emr.findOne(empId);
        emr.delete(emprunt);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
