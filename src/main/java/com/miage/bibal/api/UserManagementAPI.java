/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bibal.api;

import com.miage.bibal.entity.Usager;
import com.miage.bibal.ressource.UsagerRessource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@RequestMapping(value="/user",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserManagementAPI  {
    public UsagerRessource ur;
    
    @Autowired
    public UserManagementAPI(UsagerRessource ur){
        this.ur= ur;
    }
    
    //get all
    @GetMapping
    public ResponseEntity<?> getAllUsager(){
        Iterable<Usager> usagersCollection = ur.findAll();
        return new ResponseEntity<>(usagerToRessource(usagersCollection),HttpStatus.OK);
    }
    
    @GetMapping(value="/{usagerId}")
    public ResponseEntity<?> getUsager(@PathVariable("usagerId") String id){
        return Optional.ofNullable(ur.findOne(id))
                .map(u -> new ResponseEntity<>(usagerToRessournce(u,true),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    private Resources<Resource<Usager>> usagerToRessource(Iterable<Usager> usagers){
        Link selfLink = linkTo(methodOn(UserManagementAPI.class).getAllUsager()).withSelfRel();
        List<Resource<Usager>> usagerRessources = new ArrayList<>();
        usagers.forEach(usager -> 
                usagerRessources.add(usagerToRessournce(usager, false)));
        
        return new Resources<>(usagerRessources,selfLink);
    }
    private Resource<Usager> usagerToRessournce(Usager usager, Boolean collection){
        Link selfLink = linkTo(UserManagementAPI.class).slash(usager.getID()).withSelfRel();
        if(collection){
            Link collectionLink = linkTo(methodOn(UserManagementAPI.class).getAllUsager()).withRel("collection");
            return  new Resource<>(usager, selfLink,collectionLink);
        }else{
            return new Resource<>(usager,selfLink);
        }
        
    }
    //POST
    @PostMapping
    public ResponseEntity<?> saveIntervenant(@RequestBody Usager intervenant){
        intervenant.setID(UUID.randomUUID().toString());
        Usager saved = ur.save(intervenant);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserManagementAPI.class).slash(saved.getID()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    
    //put
    @PutMapping
    public ResponseEntity<?> updateIntervenant(@RequestBody Usager intervenant,@PathVariable("intervenantId") String intervenantId ){
        
        Optional<Usager> body= Optional.ofNullable(intervenant);
        
        if(!body.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);           
        }
        if(!ur.exists(intervenantId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        intervenant.setID(intervenantId);
        Usager result = ur.save(intervenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
     //delete
    @DeleteMapping(value="/{intervenantId}")
    public ResponseEntity<?> deleteIntervenant(@PathVariable("intervenantId") String intervenantId ){
        ur.delete(intervenantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
