/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bibal.api;

import com.miage.bibal.entity.Auteur;
import com.miage.bibal.entity.Exemplaire;
import com.miage.bibal.entity.Livre;
import com.miage.bibal.entity.Magazine;
import com.miage.bibal.entity.Oeuvre;
import com.miage.bibal.entity.Usager;
import com.miage.bibal.ressource.AuteurRessource;
import com.miage.bibal.ressource.EntityToRessource;
import com.miage.bibal.ressource.ExemplaireRessource;
import com.miage.bibal.ressource.LivreRessource;
import com.miage.bibal.ressource.MagazineRessource;
import com.miage.bibal.ressource.OeuvreRessource;
import com.miage.bibal.ressource.UsagerRessource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alex
 */
@RestController
@CrossOrigin
@RequestMapping(value="/oeuvre",produces=MediaType.APPLICATION_JSON_VALUE)
public class OeuvreManagement {
    public OeuvreRessource or;
    public AuteurRessource ar;
    public MagazineRessource mr;
    public ExemplaireRessource er;
    public LivreRessource lr;
    
    @Autowired
    public OeuvreManagement(OeuvreRessource or,AuteurRessource ar,MagazineRessource mr,ExemplaireRessource er,LivreRessource lr){
        this.or = or;
        this.ar = ar;
        this.mr = mr;
        this.er = er;
        this.lr = lr;
    }
    
    @GetMapping
    public ResponseEntity<?> getAllOeuvre(){
        Iterable<Oeuvre> oeuvreCollection = or.findAll();
        return new ResponseEntity<>(EntityToRessource.oeuvreToRessource(oeuvreCollection),HttpStatus.OK);
    }
    
    @GetMapping(value="/{oeuvreId}")
    public ResponseEntity<?> getOeuvre(@PathVariable("oeuvreId") String id){
        return Optional.ofNullable(or.findOne(id))
                .map(u -> new ResponseEntity<>(EntityToRessource.oeuvreToRessource(u,true),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping(value="/{oeuvreId}/exemplaire/")
    public ResponseEntity<?> getExemplaireByOeuvre(@PathVariable("oeuvreId") String id){
        Oeuvre oeuvre = or.findOne(id);
        Iterable<Exemplaire> lesExemplaires = oeuvre.getExemplaires();
        return new ResponseEntity<>(lesExemplaires,HttpStatus.OK);
    }
    
    @GetMapping(value="/{oeuvreId}/exemplaire/{exemplaireId}")
    public ResponseEntity<?> getExemplaire(@PathVariable("oeuvreId") String id,@PathVariable("exemplaireId") String idExemplaire){
        return Optional.ofNullable(er.findOne(idExemplaire))
                .map(u -> new ResponseEntity<>(u,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));    
    }
    
    @GetMapping(value="/auteur/")
    public ResponseEntity<?> getAllAuteur(){
        Iterable<Auteur> auteurCollection = ar.findAll();
        return new ResponseEntity<>(EntityToRessource.auteurToRessource(auteurCollection),HttpStatus.OK);
    }
    
    @GetMapping(value="/auteur/{idAuteur}")
    public ResponseEntity<?> getAuteur(@PathVariable("idAuteur") String id){
        return Optional.ofNullable(ar.findOne(id))
                .map(u -> new ResponseEntity<>(EntityToRessource.auteurToRessource(u,true),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    //POST
    @PostMapping
    public ResponseEntity<?> saveOeuvre(@RequestBody Oeuvre oeuvre){
        oeuvre.setID(UUID.randomUUID().toString());
        Oeuvre saved = or.save(oeuvre);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
     //POST
    @PostMapping(value="/magazine/")
    public ResponseEntity<?> saveMagazine(@RequestBody Magazine oeuvre){
        oeuvre.setID(UUID.randomUUID().toString());
        Magazine saved = mr.save(oeuvre);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    
      //POST
    @PostMapping(value="/livre/")
    public ResponseEntity<?> saveLivre(@RequestBody Livre oeuvre){
        oeuvre.setID(UUID.randomUUID().toString());
        Livre saved = lr.save(oeuvre);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    
    //POST
    @PostMapping("/auteur")
    public ResponseEntity<?> saveAuteur(@RequestBody Auteur auteur){
        auteur.setID(UUID.randomUUID().toString());
        Auteur saved = ar.save(auteur);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    //POST
    @PostMapping(value="/{oeuvreId}/exemplaire/")
    public ResponseEntity<?> saveExemplaire(@PathVariable("oeuvreId") String id,@RequestBody Exemplaire exemplaire){
        
        Oeuvre oeuvre = or.findOne(id);
        oeuvre.getExemplaires().add(exemplaire);
        exemplaire.setId(UUID.randomUUID().toString());
        exemplaire.setOeuvre(oeuvre);
        Exemplaire saved = er.save(exemplaire);
        Oeuvre savedo = or.save(oeuvre);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    
    //put
    @PutMapping("/exemplaire/{exemplaireId}")
    public ResponseEntity<?> updateExemplaire(@RequestBody Exemplaire exemplaire,@PathVariable("exemplaireId") String exemplaireId ){
        
        Optional<Exemplaire> body= Optional.ofNullable(exemplaire);
        
        if(!body.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);           
        }
        if(!er.exists(exemplaireId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exemplaire.setId(exemplaireId);
        Exemplaire result = er.save(exemplaire);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //delete
    @DeleteMapping(value="/{oeuvreId}")
    public ResponseEntity<?> deleteOeuvre(@PathVariable("oeuvreId") String oeuvreId ){
        Oeuvre oeuvre = or.findOne(oeuvreId);
        oeuvre.getExemplaires().forEach(exemplaire->er.delete(exemplaire.getId()));
        or.delete(oeuvreId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //delete
    @DeleteMapping(value="/exemplaire/{exemplaireId}")
    public ResponseEntity<?> deleteIntervenant(@PathVariable("exemplaireId") String exemplaireId ){
        er.delete(exemplaireId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
